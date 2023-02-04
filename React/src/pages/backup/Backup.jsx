import {Restore, Save} from '@mui/icons-material';
import {
    AlertTitle,
    Button,
    CircularProgress,
    FormControl,
    FormHelperText,
    InputLabel, LinearProgress,
    MenuItem,
    Paper,
    Select
} from '@mui/material';
import React from 'react';
import {useState} from 'react';
import Navbar from '../../components/navbar/Navbar';
import Sidebar from '../../components/sidebar/Sidebar';
import axios from 'axios';
import { useSelector } from 'react-redux';
import './backup.scss';
import Alert from "@mui/material/Alert";
import URL from '../../store/url';

const Backup = () => {
    
    const [databaseName, setDatabaseName] = useState('');
    const [isBackupLoading, setIsBackupLoading] = useState(false);
    const [isRestoreLoading, setIsRestoreLoading] = useState(false);
    const [backupCode, setBackupCode] = useState(true);
    const [restoreCode, setRestoreCode] = useState(true);
    const [showBackupMsg, setShowBackupMsg] = useState(false);
    const [showRestoreMsg, setShowRestoreMsg] = useState(false);

    const token = useSelector(state => state.auth.tokenId);

    const date1 = new Date("2022", "01", "01");
    const [file, setFile] = useState();

    const handleOnChange = (e) => {
        setFile(e.target.files[0]);
    };

    const close = (e) => {
        setShowBackupMsg(false);
        setShowRestoreMsg(false);
    };

    const backup = (e) => {
        setIsBackupLoading(true);
        axios.get(`${URL}/api/database_backup`, {
            headers: {
                "Authorization": `Bearer ${token}`,
            }
        })
            .then(response => {
                setIsBackupLoading(false);
                if (JSON.stringify(response.data) == 0) {
                    setBackupCode(true);
                } else {
                    setBackupCode(false);
                }
                setShowBackupMsg(true);
            })
            .catch(error => {
                console.log("ERROR:: ", error.response.data);
            });
    };

    const restore = (e) => {
        setIsRestoreLoading(true);
        axios.get(`${URL}/api/database_restore`, {
            headers: {
                "Authorization": `Bearer ${token}`,
            }
        })
            .then(response => {
                setIsRestoreLoading(false);
                if (JSON.stringify(response.data) == 0) {
                    setRestoreCode(true);
                } else {
                    setRestoreCode(false);
                }
                setShowRestoreMsg(true);
            })
            .catch(error => {
                console.log("ERROR:: ", error.response.data);
            })
    };

    return (
        <div className="backup">
            <Sidebar/>
            <div className="homeContainer">
                <Navbar/>
                <div>
                    {(showBackupMsg == true) && (backupCode == false) &&
                        <Alert severity="error" onClose={() => {
                            close()
                        }}>
                            <AlertTitle>Error</AlertTitle>
                            Database Backup Failed. — <strong>Please try again!</strong>
                        </Alert>}
                    {(showRestoreMsg == true) && (restoreCode == false) &&
                        <Alert severity="error" onClose={() => {
                            close()
                        }}>
                            <AlertTitle>Error</AlertTitle>
                            Database Restore Failed — <strong>Please try again!</strong>
                        </Alert>}
                    {(showBackupMsg == true) && (backupCode == true) &&
                        <Alert severity="success" onClose={() => {
                            close()
                        }}>
                            <AlertTitle>Success</AlertTitle>
                            Database Backup Success! — <strong>check it out!</strong>
                        </Alert>}
                    {(showRestoreMsg == true) && (restoreCode == true) &&
                        <Alert severity="success" onClose={() => {
                            close()
                        }}>
                            <AlertTitle>Success</AlertTitle>
                            Database Restore Success! — <strong>check it out!</strong>
                        </Alert>}
                </div>
                <div className="backupContent">
                    <Paper elevation={2}>

                        <div className="contentTitle">
                            Database Backup
                        </div>
                        <div className="contentItem">
                            {!isBackupLoading &&
                                <Button variant="contained" color="success" startIcon={<Save/>} size="large"
                                        onClick={backup}>
                                    Save Current State
                                </Button>}
                            {isBackupLoading && <CircularProgress/>}

                        </div>
                    </Paper>
                    <Paper elevation={2}>
                        <div className="contentTitle">
                            Database Restore
                        </div>
                        <div className="contentItem">
                            {!isRestoreLoading && <Button variant="contained" color="success" endIcon={<Restore/>}
                                                          size="large" onClick={restore}>Restore</Button>}
                            {isRestoreLoading && <CircularProgress/>}


                        </div>
                    </Paper>
                </div>
            </div>
        </div>
    );
};

export default Backup;

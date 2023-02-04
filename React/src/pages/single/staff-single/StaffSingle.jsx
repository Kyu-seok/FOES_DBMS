import React, {useEffect, useState} from 'react';
import {useNavigate, useParams} from 'react-router-dom';
import Input from '../../../components/input/Input';
import Navbar from '../../../components/navbar/Navbar';
import Sidebar from '../../../components/sidebar/Sidebar';
import {Button} from '@mui/material';
import './staffSingle.scss';
import {useSelector} from 'react-redux';
import URL from '../../../store/url';

const StaffSingle = () => {

    const token = useSelector(state => state.auth.tokenId);
    const [entry, setEntry] = useState({});
    const params = useParams();
    const {id} = params;
    const navigate = useNavigate();

    useEffect(() => {
        fetch(`${URL}/staffs/${id}`, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        })
            .then(response => response.json())
            .then(data => {
                setEntry(data);
            });
    }, [id, token]);

    const generateForm = (obj) => {
        let formHtml = [];

        for (const key in obj) {
            if (!(key == 'id' || key == 'created_at' || key == 'updated_at')) {
                if (key == 'resigned_date' || key == 'report_duty_date') {
                    formHtml.push(
                        <Input name={key} hint=" (yyyy-mm-dd)" key={key} initialValue={obj[key]}
                               onFormChangeHandler={onFormChangeHandler}/>
                    );
                } else if (key == 'email') {
                    formHtml.push(
                        <Input name={key} hint=" (eg: name@email.com)" key={key} initialValue={obj[key]}
                               onFormChangeHandler={onFormChangeHandler}/>
                    );
                } else {
                    formHtml.push(
                        <Input name={key} key={key} initialValue={obj[key]} onFormChangeHandler={onFormChangeHandler}/>
                    );
                }
            }
        }
        return formHtml;
    };

    const onFormChangeHandler = (key, value) => {
        let newEntry = entry;
        newEntry[key] = value;
        setEntry(newEntry);
    };

    const generatedForm = generateForm(entry);

    const onUpdateHandler = (event) => {
        event.preventDefault();
        fetch(`${URL}/staffs/${id}`, {
            method: 'PUT',
            body: JSON.stringify(entry),
            headers: {
                Authorization: `Bearer ${token}`,
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                if (response.ok) {
                    navigate('/staffs');
                    return response.json();
                }
                return Promise.reject(response);
            })
            .catch(response => {
                response.json().then(json => alert(json.message));
            });
    };

    return (
        <div className="single">
            <Sidebar/>
            <div className="homeContainer">
                <Navbar/>
                <div className="title">Update</div>
                <div className="content">
                    <div className="singleEntry">
                        <form className='entryForm' onSubmit={onUpdateHandler}>
                            {generatedForm}
                            <Button type='submit'>Update</Button>
                        </form>
                        <div className="break"/>
                    </div>
                </div>
                <div className="content">
                </div>
            </div>
        </div>
    );
};

export default StaffSingle;

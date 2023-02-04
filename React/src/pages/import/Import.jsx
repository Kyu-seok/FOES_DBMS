import React, {useState} from 'react'
import Navbar from '../../components/navbar/Navbar';
import Sidebar from '../../components/sidebar/Sidebar';
import './import.scss';

const Import = () => {
  
    return (
        <div className="setting">
            <Sidebar/>
            <div className="homeContainer">
                <Navbar/>
                <p> Data Import feature is temporally removed </p>
                <br/>
            </div>
        </div>
    );
}

export default Import

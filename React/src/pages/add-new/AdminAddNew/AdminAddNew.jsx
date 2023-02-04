import { useSelector } from 'react-redux';
import { useState } from 'react';

import './adminAddNew.scss';
import { useNavigate } from 'react-router-dom';
import { Button } from '@mui/material';
import Sidebar from '../../../components/sidebar/Sidebar';
import Navbar from '../../../components/navbar/Navbar';
import InputPassword from '../../../components/input-password/InputPassword';
import { useEffect } from 'react';
import InputEmail from '../../../components/input-email/InputEmail';
import InputNormal from '../../../components/input-normal/InputNormal';
import URL from '../../../store/url';

const AdminAddNew = () => {
    const token = useSelector(state => state.auth.tokenId);
    const navigate = useNavigate();

    const [userName, setUserName] = useState("");
    const [isUserNameValid, setIsUserNameValid] = useState(false);

    const [password, setPassword] = useState("");
    const [isPasswordValid, setIsPasswordValid] = useState(false);

    const [firstName, setFirstName] = useState("");
    const [isFirstNameValid, setIsFirstNameValid] = useState(false);

    const [lastName, setLastName] = useState("");
    const [isLastNameValid, setIsLastNameValid] = useState(false);

    const [email, setEmail] = useState("");
    const [isEmailValid, setIsEmailValid] = useState(true);

    const [phoneNumber, setPhoneNumber] = useState("");
    const [isPhoneNumberValid, setIsPhoneNumberValid] = useState(true);

    const [isFormValid, setIsFormValid] = useState(false);

    useEffect(() => {
        if (isUserNameValid && isPasswordValid && isEmailValid && isFirstNameValid && isLastNameValid ) {
            setIsFormValid(true);
        } else {
            setIsFormValid(false);
        }
    }, [isUserNameValid, isPasswordValid, isEmailValid, isFirstNameValid, isLastNameValid]);

    const submitHandler = (event) => {
        event.preventDefault();

        const jsonObject = {
            "userName": userName,
            "password": password,
            "firstName": firstName,
            "lastName": lastName,
            "email": email,
            "phoneNumber": phoneNumber,
        };

        fetch(`${URL}/users`, {
            method: 'POST',
            body: JSON.stringify(jsonObject),
            headers: {
                Authorization: `Bearer ${token}`,
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                if (response.ok) {
                    (navigate('/'));
                    return response.json();
                }
                return Promise.reject(response);
            })
            .catch(response => {
                response.json().then(json => alert(json.message));
            });

    };

    return (
        <div className="new">
            <Sidebar />
            <div className="newContainer">
                <Navbar />
                <div className="title">
                    <p>Admin</p>
                </div>
                <div className="bottom">
                    <form onSubmit={submitHandler}>

                        <InputNormal value={userName} setHandler={setUserName} label="User Account" isValid={isUserNameValid} setIsValid={setIsUserNameValid} />

                        <InputPassword value={password} setHandler={setPassword} label="Password" isValid={isPasswordValid} setIsValid={setIsPasswordValid} />

                        <InputNormal value={firstName} setHandler={setFirstName} label="First Name" isValid={isFirstNameValid} setIsValid={setIsFirstNameValid} />

                        <InputNormal value={lastName} setHandler={setLastName} label="Last Name" isValid={isLastNameValid} setIsValid={setIsLastNameValid} />

                        <InputEmail value={email} setHandler={setEmail} label="Email Address" isValid={isEmailValid} setIsValid={setIsEmailValid} />

                        <InputNormal value={phoneNumber} setHandler={setPhoneNumber} label="Phone Number" isValid={isPhoneNumberValid} setIsValid={setIsPhoneNumberValid} />

                        <Button disabled={!isFormValid} type='submit'>Send</Button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default AdminAddNew;

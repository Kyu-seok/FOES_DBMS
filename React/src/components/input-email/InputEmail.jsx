import React from 'react';
import { useEffect } from 'react';
import { useState } from 'react';
import './inputEmail.scss';

const InputEmail = (props) => {

  const isValid = props.isValid;
  const setIsValid = props.setIsValid;
  const [isEntered, setIsEntered] = useState(false);

  const value = props.value;

  useEffect(() => {
    if (value != "") {
      setIsEntered(true);
    } else {
      setIsEntered(false);
      setIsValid(true);
    }

    if (isEntered) {
      if (checkValidInput(value)) {
        setIsValid(true);
      } else {
        setIsValid(false);
      }
    }

  }, [value, isEntered, setIsValid]);


  const checkValidInput = (input) => {
    var pass = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    if (input.match(pass)) {
      return true;
    }
    else {
      return false;
    }
  };

  const onInputChangeHandler = (event) => {
    props.setHandler(event.target.value);
  };

  return (
    <div className={isValid ? 'formInput' : 'formInput--invalid'}>
      <label>{props.label}</label>
      <input type="text" onChange={onInputChangeHandler} value={value} placeholder="example@email.com"></input>
    </div>
  );
};

export default InputEmail;
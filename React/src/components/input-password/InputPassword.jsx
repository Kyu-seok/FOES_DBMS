import React from 'react';
import { useEffect } from 'react';
import { useState } from 'react';
import './inputPassword.scss';

const InputPassword = (props) => {
  const isValid = props.isValid;
  const setIsValid = props.setIsValid;
  const [isEntered, setIsEntered] = useState(false);

  const value = props.value;

  useEffect(() => {
    if (value != "") {
      setIsEntered(true);
    } else {
      setIsEntered(false);
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
    var pass = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\s).{8,20}$/;
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
    <div className={(isEntered && !isValid)? 'formInput--invalid' : 'formInput' }>
      <label>{props.label}</label>
      <input type="password" onChange={onInputChangeHandler} placeholder="1 uppercase, 1 lowercase, 1 symbol, 1 numeric character, min 8 digit"></input>
    </div>
  );
};

export default InputPassword;

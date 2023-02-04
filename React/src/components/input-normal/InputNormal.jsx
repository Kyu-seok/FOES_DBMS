import React from 'react';
import { useEffect } from 'react';
import { useState } from 'react';
import './inputNormal.scss';

const InputNormal = (props) => {

  const setIsValid = props.setIsValid;

  const value = props.value;

  useEffect(() => {
    if (value != "") {
      setIsValid(true);
    } else {
      setIsValid(false);
    }

  }, [value, setIsValid]);

  const onInputChangeHandler = (event) => {
    props.setHandler(event.target.value);
  };

  return (
    <div className='formInput'>
      <label>{props.label}</label>
      <input type="text" onChange={onInputChangeHandler}></input>
    </div>
  );
};

export default InputNormal;
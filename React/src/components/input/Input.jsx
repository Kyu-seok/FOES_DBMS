import React from 'react';
import { useState } from 'react';
import './input.scss';

const Input = (props) => {

  const [value, setValue] = useState(props.initialValue);

  const onChangeHandler = (event) => {
    setValue(event.target.value);
    props.onFormChangeHandler(props.name, event.target.value);
  }

  const label = props.hint 
    ? (<label>{props.name + props.hint}</label>)
    : (<label>{props.name}</label>);

  return (
    <div className="formInput">
      <label>{label}</label>
      <input type={props.type == null ? "text" : props.type} name={props.name} value={value} onChange={(event) => onChangeHandler(event)} />
    </div>
  );
};

export default Input;
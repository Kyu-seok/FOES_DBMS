import { useSelector } from 'react-redux';
import { useRef } from 'react';
import './mobilityAddNew.scss';
import { useNavigate } from 'react-router-dom';
import { Button } from '@mui/material';
import Sidebar from '../../../components/sidebar/Sidebar';
import Navbar from '../../../components/navbar/Navbar';
import URL from '../../../store/url';

const MobilityAddNew = () => {
  const token = useSelector(state => state.auth.tokenId)
  const navigate = useNavigate();

  const staffStudentInput = useRef(null);
  const inboundOutboundInput = useRef(null);
  const nameInput = useRef(null);
  const studentIdStaffIdInput = useRef(null);
  const programInput = useRef(null);
  const nameOfUniversityInput = useRef(null);
  const countryInput = useRef(null);
  const fromInput = useRef(null);
  const toInput = useRef(null);
  const durationsInput = useRef(null);
  const remarkInput = useRef(null);

  const listRef = useRef([]);

  const submitHandler = (event) => {
    event.preventDefault();
    const jsonObject = {
      "staffOrStudent": staffStudentInput.current.value,
      "inOrOutBound": inboundOutboundInput.current.value,
      "name": nameInput.current.value,
      "attendeeId": studentIdStaffIdInput.current.value,
      "program": programInput.current.value,
      "nameOfUniversity": nameOfUniversityInput.current.value,
      "country": countryInput.current.value,
      "duration": durationsInput.current.value,
      "fromDate": fromInput.current.value === "" ? null : fromInput.current.value,
      "toDate": toInput.current.value === "" ? null : toInput.current.value,
      "remark": remarkInput.current.value,
    };

    listRef.current.forEach(el => {
      if (el.value) {
        jsonObject[`${el.name}`] = el.value;
      }
    })

    fetch(`${URL}/mobilities`, {
      method: 'POST',
      body: JSON.stringify(jsonObject),
      headers: {
        Authorization : `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    })
      .then(response => {
        if (response.ok) {
          navigate('/mobilities');
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
          <p>Mobility</p>
        </div>
        <div className="bottom">
          <form onSubmit={submitHandler}>

            <div className="formInput">
              <label>Staff/Student</label>
              <select name="staffStudent" ref={staffStudentInput}>
                <option value="Staff">Staff</option>
                <option value="Student">Student</option>
              </select>
            </div>
            <div className="formInput">
              <label>Inbound/Outbound</label>
              <select name="inboundOutbound" ref={inboundOutboundInput}>
                <option value="Inbound">Inbound</option>
                <option value="Outbound">Outbound</option>
              </select>
            </div>
            <div key='name' className="formInput" >
              <label>Name</label>
              <input type="text" name="name" ref={nameInput} />
            </div>
            <div key='studentIdStaffId' className="formInput" >
              <label>Student ID/ Staff ID</label>
              <input type="text" name="studentIdStaffId" ref={studentIdStaffIdInput} />
            </div>
            <div key='program' className="formInput" >
              <label>Program</label>
              <input type="text" name="program" ref={programInput} />
            </div>
            <div key='nameOfUniversity' className="formInput" >
              <label>Name of University</label>
              <input type="text" name="nameOfUniversity" ref={nameOfUniversityInput} />
            </div>
            <div key='country' className="formInput" >
              <label>Country</label>
              <input type="text" name="country" ref={countryInput} />
            </div>
            <div key='from' className="formInput">
              <label>From</label>
              <input type="date" name="from" ref={fromInput}></input>
            </div>
            <div key='to' className="formInput">
              <label>To</label>
              <input type="date" name="to" ref={toInput}></input>
            </div>
            {/* date input */}
            <div key='durations' className="formInput" >
              <label>Durations (days)</label>
              <input type="text" name="durations" ref={durationsInput} />
            </div>
            <div key='remarks' className="formInput" >
              <label>Remarks</label>
              <input type="text" name="remarks" ref={remarkInput} />
            </div>
            <Button type='submit'>Send</Button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default MobilityAddNew;

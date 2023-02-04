import { useSelector } from 'react-redux';
import { useRef } from 'react';
import './ktpUsrAddNew.scss';
import { useNavigate } from 'react-router-dom';
import { Button } from '@mui/material';
import Sidebar from '../../../components/sidebar/Sidebar';
import Navbar from '../../../components/navbar/Navbar';
import URL from '../../../store/url';

const KtpUsrAddNew = () => {
  const token = useSelector(state => state.auth.tokenId)
  const navigate = useNavigate();

  const programCategoryInput = useRef(null);
  const nameOfUsrKtpProgramInput = useRef(null);
  const communityIndustryInput = useRef(null);
  const eventDateInput = useRef(null);
  const locationInput = useRef(null);
  const leadByInput = useRef(null);
  const CMsDrivenInput = useRef(null);
  const facultyDepartmentInput = useRef(null);
  const nameOfPartnerInput = useRef(null);
  const noOfStaffInput = useRef(null);
  const noOfStudentInput = useRef(null);
  const internalFundingInput = useRef(null);
  const externalFundingInput = useRef(null);
  const remarkInput = useRef(null);

  const listRef = useRef([]);

  const submitHandler = (event) => {
    event.preventDefault();
    const jsonObject = {
      "category": programCategoryInput.current.value,
      "date": eventDateInput.current.value === "" ? null : eventDateInput.current.value,
      "programName": nameOfUsrKtpProgramInput.current.value,
      "communityIndustryName": communityIndustryInput.current.value,
      "location": locationInput.current.value,
      "leadBy": leadByInput.current.value,
      "faculty": facultyDepartmentInput.current.value,
      "cmDriven": CMsDrivenInput.current.value,
      "partnerName": nameOfPartnerInput.current.value,
      "noOfStaff": noOfStaffInput.current.value,
      "noOfStudent": noOfStudentInput.current.value,
      "internalFunding": internalFundingInput.current.value,
      "externalFunding": externalFundingInput.current.value,
      "remark": remarkInput.current.value
    };

    listRef.current.forEach(el => {
      if (el.value) {
        jsonObject[`${el.name}`] = el.value;
      }
    })

    fetch(`${URL}/ktp-usrs`, {
      method: 'POST',
      body: JSON.stringify(jsonObject),
      headers: {
        Authorization : `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    })
    .then(response => {
      if (response.ok) {
        navigate('/ktp-usrs');
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
          <p>KTP USR</p>
        </div>
        <div className="bottom">
          <form onSubmit={submitHandler}>

            <div className="formInput">
              <label>Program Category</label>
              <select name="programCategory" ref={programCategoryInput}>
                <option value="KTP">KTP</option>
                <option value="USR">USR</option>
              </select>
            </div>

            <div key='nameOfProgram' className="formInput" >
              <label>Name of USR/KTP Program</label>
              <input type="text" name="nameOfProgram" ref={nameOfUsrKtpProgramInput} />
            </div>
            <div key='communityIndustry' className="formInput" >
              <label>Community/Industry</label>
              <input type="text" name="communityIndustry" ref={communityIndustryInput} />
            </div>
            <div key='eventDAte' className="formInput">
              <label>Event Date</label>
              <input type="date" name="eventDate" ref={eventDateInput}></input>
            </div>
            <div key='location' className="formInput" >
              <label>Location</label>
              <input type="text" name="location" ref={locationInput} />
            </div>
            <div key='leadBy' className="formInput" >
              <label>Lead By</label>
              <input type="text" name="leadBy" ref={leadByInput} />
            </div>
            <div key='cmDriven' className="formInput" >
              <label>CM's Driven</label>
              <input type="text" name="cmDriven" ref={CMsDrivenInput} />
            </div>
            <div className="formInput">
              <label>Faculty/Department</label>
              <select name="facultyDepartment" ref={facultyDepartmentInput}>
                <option value="Faculty Office">Faculty Office</option>
                <option value="Lab">Lab</option>
                <option value="Applied Sciences">Applied Sciences</option>
                <option value="Chemical & Energy">Chemical & Energy</option>
                <option value="Civil & Construction">Civil & Construction</option>
                <option value="Electrical & Computer">Electrical & Computer</option>
                <option value="Mechanical">Mechanical</option>
                <option value="Foundation">Foundation</option>
              </select>
            </div>
            <div key='nameOfPartner' className="formInput" >
              <label>Name of Partner</label>
              <input type="text" name="nameOfPartner" ref={nameOfPartnerInput} />
            </div>
            <div key='noStaff' className="formInput" >
              <label>No. of Staff</label>
              <input type="text" name="noStaff" ref={noOfStaffInput} />
            </div>
            <div key='noStudent' className="formInput" >
              <label>No. of Student</label>
              <input type="text" name="noStudent" ref={noOfStudentInput} />
            </div>
            <div key='internal' className="formInput" >
              <label>Internal Funding</label>
              <input type="text" name="internal" ref={internalFundingInput} />
            </div>
            <div key='external' className="formInput" >
              <label>External Funding</label>
              <input type="text" name="external" ref={externalFundingInput} />
            </div>
            <div key='remark' className="formInput" >
              <label>Remark</label>
              <input type="text" name="remark" ref={remarkInput} />
            </div>
            <Button type='submit'>Send</Button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default KtpUsrAddNew;

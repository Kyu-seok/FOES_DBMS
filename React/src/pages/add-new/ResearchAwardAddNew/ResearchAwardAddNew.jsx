import { useSelector } from 'react-redux';
import { useRef } from 'react';
import './researchAwardAddNew.scss';
import { useNavigate } from 'react-router-dom';
import { Button } from '@mui/material';
import Sidebar from '../../../components/sidebar/Sidebar';
import Navbar from '../../../components/navbar/Navbar';
import URL from '../../../store/url';

const ResearchAwardAddNew = () => {
  const token = useSelector(state => state.auth.tokenId)
  const navigate = useNavigate();
  const staffIdInput = useRef(null);
  const typeOfGrantInput = useRef(null);
  const projectTitleInput = useRef(null);
  const coInvestigatorInput = useRef(null);
  const researchGrantSchemeInput = useRef(null);
  const awardAmountInput = useRef(null);
  const evidenceLinkInput = useRef(null);

  const listRef = useRef([]);

  const submitHandler = (event) => {
    event.preventDefault();
    const jsonObject = {
      "staffId": staffIdInput.current.value,
      "typeOfGrant": typeOfGrantInput.current.value,
      "projectTitle": projectTitleInput.current.value,
      "coInvestigators": coInvestigatorInput.current.value,
      "researchGrantScheme": researchGrantSchemeInput.current.value,
      "awardAmount": awardAmountInput.current.value,
      "evidenceLink": evidenceLinkInput.current.value,
    };

    listRef.current.forEach(el => {
      if (el.value) {
        jsonObject[`${el.name}`] = el.value;
      }
    })

    fetch(`${URL}/research-awards`, {
      method: 'POST',
      body: JSON.stringify(jsonObject),
      headers: {
        Authorization : `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    })
    .then(response => {
      if (response.ok) {
        navigate('/research-awards');
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
          <p>Research-Award</p>
        </div>
        <div className="bottom">
          <form onSubmit={submitHandler}>

            <div key='staffId' className="formInput" >
              <label>Staff ID</label>
              <input type="text" min='0' name="staffId" ref={staffIdInput} />
            </div>
            <div key='typeOfGrant' className="formInput" >
              <label>Type of Grant</label>
              <input type="text" name="typeOfGrant" ref={typeOfGrantInput} />
            </div>
            <div key='projectTitle' className="formInput" >
              <label>Project Title</label>
              <input type="text" name="projectTitle" ref={projectTitleInput} />
            </div>
            <div key='coInvestigators' className="formInput" >
              <label>Co-investigators</label>
              <input type="text" name="conInvestigators" ref={coInvestigatorInput} />
            </div>
            <div key='researchGrantScheme' className="formInput" >
              <label>Research Grant Scheme</label>
              <input type="text" name="researchGrantScheme" ref={researchGrantSchemeInput} />
            </div>
            <div key='awardAmount' className="formInput" >
              <label>Award Amount</label>
              <input type="text" name="awardAmount" ref={awardAmountInput} />
            </div>
            <div key='evidenceLink' className="formInput" >
              <label>Evidence Link</label>
              <input type="text" name="evidenceLink" ref={evidenceLinkInput} />
            </div>
            <Button type='submit'>Send</Button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default ResearchAwardAddNew;

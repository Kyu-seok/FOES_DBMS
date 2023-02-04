import { Link } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { Transform, ExitToAppOutlined, Badge, SupervisorAccount, HomeWork, WorkspacePremium, LocationCity, Equalizer, Backup, InputOutlined } from '@mui/icons-material';
import { uiActions } from '../../store/ui-slice';

import './sidebar.scss';
import { authActions } from '../../store/auth-slice';
import URL from '../../store/url';

const Sidebar = () => {
  const token = useSelector(state => state.auth.tokenId);
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const isAdmin = useSelector(state => state.auth.isAdmin);

  const darkModeHandler = () => {
    dispatch(uiActions.setDarkMode());
  };

  const lightModeHandler = () => {
    dispatch(uiActions.setLightMode());
  };

  const logoutHandler = () => {
    dispatch(authActions.logout());
    navigate('/');
  };

  return (
    <div className="sidebar">
      <div className="top">
        <Link to='/' className='link'>
          <span className='logo'>FoES DBMS</span>
        </Link>
      </div>
      <hr />
      <div className="center">
        <ul>
          <p className="title">DATABASE</p>

          {isAdmin && (<Link to='/users' className='link'>
            <li>
              <Badge className='icon' />
              <span>User</span>
            </li>
          </Link>)}

          {!isAdmin && (
            <>
              <Link to='/staffs' className='link'>
                <li>
                  <SupervisorAccount className='icon' />
                  <span>Staff</span>
                </li>
              </Link>

              <Link to='/assets' className='link'>
                <li>
                  <HomeWork className='icon' />
                  <span>Asset</span>
                </li>
              </Link>

              <Link to='/research-awards' className='link'>
                <li>
                  <WorkspacePremium className='icon' />
                  <span>Research-Award</span>
                </li>
              </Link>

              <Link to='/mou-moas' className='link'>
                <li>
                  <LocationCity className='icon' />
                  <span>MOU-MOA</span>
                </li>
              </Link>

              <Link to='/ktp-usrs' className='link'>
                <li>
                  <Equalizer className='icon' />
                  <span>KTP-USR</span>
                </li>
              </Link>

              <Link to='/mobilities' className='link'>
                <li>
                  <Transform className='icon' />
                  <span>Mobility</span>
                </li>
              </Link>

            </>
          )}

          {!isAdmin && (
            <>
              <p className='title'>FEATURES</p>
              <Link to='/import' className='link'>
                <li>
                  <InputOutlined className='icon' />
                  <span>Data Import</span>
                </li>
              </Link>
            </>
          )}
          {isAdmin && (
            <>
              <p className='title'>FEATURES</p>
              <Link to='/backup' className='link'>
                <li>
                  <Backup className='icon' />
                  <span>Backup & Restore</span>
                </li>
              </Link>
            </>
          )}
          <p className='title'>USER</p>
          <li onClick={logoutHandler}>
            <ExitToAppOutlined className='icon' />
            <span>Logout</span>
          </li>
        </ul>
      </div>
      <div className="bottom">
        <div className="colorOption" onClick={lightModeHandler}></div>
        <div className="colorOption" onClick={darkModeHandler}></div>
      </div>
    </div>
  );
};

export default Sidebar;

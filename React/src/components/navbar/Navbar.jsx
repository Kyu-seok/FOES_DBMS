import './navbar.scss';
import MaterialUISwitch from '../mui-switch/MaterialUISwitch';
import { useDispatch, useSelector } from 'react-redux';
import { uiActions } from '../../store/ui-slice';

const Navbar = () => {

  const dispatch = useDispatch();
  const isDarkMode = useSelector(state => state.ui.isDarkMode);

  const onToggleHandler = () => {
    dispatch(uiActions.toggleDarkMode());
  };

  return (
    <div className='navbar'>
      <div className="wrapper">
        <div className="items">
          <div className="item">
            <MaterialUISwitch sx={{ m: 1 }} checked={isDarkMode} onClick={onToggleHandler} />
          </div>
        </div>
      </div>
    </div>
  );
};

export default Navbar;
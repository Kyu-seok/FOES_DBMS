import {BrowserRouter, Route, Routes} from 'react-router-dom';
import Login from './pages/login/Login';
import Import from './pages/import/Import';

import {useDispatch, useSelector} from 'react-redux';
import './style/dark.scss';
import Backup from './pages/backup/Backup';
import {authActions} from './store/auth-slice';
import {useEffect} from 'react';
import Admin from './pages/dashboard/admin/admin';
import Staff from './pages/dashboard/staff/staff';
import AdminAddNew from './pages/add-new/AdminAddNew/AdminAddNew';
import Asset from './pages/dashboard/asset/asset';
import ResearchAward from './pages/dashboard/research-award/researchAward';
import MouMoa from './pages/dashboard/mou-moa/mouMoa';
import KtpUsr from './pages/dashboard/ktp-usr/ktpUsr';
import Mobility from './pages/dashboard/mobility/mobility';
import StaffAddNew from './pages/add-new/StaffAddNew/StaffAddNew';
import AssetAddNew from './pages/add-new/AssetAddNew/AssetAddNew';
import ResearchAwardAddNew from './pages/add-new/ResearchAwardAddNew/ResearchAwardAddNew';
import MouMoaAddNew from './pages/add-new/MouMoaAddNew/MouMoaAddNew';
import KtpUsrAddNew from './pages/add-new/KtpUsrAddNew/KtpUsrAddNew';
import MobilityAddNew from './pages/add-new/MobilityAddNew/MobilityAddNew';
import AdminSingle from './pages/single/admin-single/AdminSingle';
import StaffSingle from './pages/single/staff-single/StaffSingle';
import AssetSingle from './pages/single/asset-single/AssetSingle';
import ResearchAwardSingle from './pages/single/research-award-single/ResearchAwardSingle';
import MouMoaSingle from './pages/single/mou-moa-single/MouMoaSingle';
import KtpUsrSingle from './pages/single/ktp-usr/KtpUsrSingle';
import MobilitySingle from './pages/single/mobility-single/MobilitySingle';

function App() {
  const dispatch = useDispatch();
  const isLoggedIn = useSelector(state => state.auth.isLoggedIn);
  const isDarkMode = useSelector(state => state.ui.isDarkMode);
  const isAdmin = useSelector(state => state.auth.isAdmin)

  const storedToken = localStorage.getItem('token');
  const storedIsSuperUser = localStorage.getItem('isAdmin');
  const exp = localStorage.getItem('exp');

  useEffect(() => {
    const remainingTime = calculateRemainingTime(exp);

    if (storedToken && (remainingTime > 10)) {
      dispatch(authActions.login([ storedToken, storedIsSuperUser == 1 ? true : false, exp]));
    } else {
      dispatch(authActions.logout())
    }
  }, [storedToken, storedIsSuperUser, exp, dispatch])

  useEffect(() => {
    const remainingTime = calculateRemainingTime(exp) ;
    const logoutTimer = setTimeout(() => {
      dispatch(authActions.logout());
    }, remainingTime * 1000)
    return () => clearTimeout(logoutTimer);
  }, [exp, dispatch])

  const calculateRemainingTime = (expirationTime) => {
    const currentTime = Date.now() / 1000;
    const remainingDuration = expirationTime - currentTime;
  
    return remainingDuration;
  };
  

  return (
    <div className={isDarkMode ? 'app dark' : 'app'}>
      <BrowserRouter>
        <Routes>
          {isLoggedIn && isAdmin && <Route path='/' element={<Admin />} />}
          {isLoggedIn && !isAdmin && <Route path='/' element={<Staff />} />}
          {isLoggedIn && isAdmin && <Route path='/users' element={<Admin />} />}
          {isLoggedIn && isAdmin && <Route path='/users/new' element={<AdminAddNew />} />}
          {isLoggedIn && isAdmin && <Route path='/users/:id' element={<AdminSingle />} />}

          {isLoggedIn && <Route path='/staffs' element={<Staff />} />}
          {isLoggedIn && <Route path='/staffs/new' element={<StaffAddNew />} />}
          {isLoggedIn && <Route path='/staffs/:id' element={<StaffSingle />} />}

          {isLoggedIn && <Route path='/assets' element={<Asset />} />}
          {isLoggedIn && <Route path='/assets/new' element={<AssetAddNew />} />}
          {isLoggedIn && <Route path='/assets/:id' element={<AssetSingle />} />}

          {isLoggedIn && <Route path='/research-awards' element={<ResearchAward />} />}
          {isLoggedIn && <Route path='/research-awards/new' element={<ResearchAwardAddNew />} />}
          {isLoggedIn && <Route path='/research-awards/:id' element={<ResearchAwardSingle />} />}

          {isLoggedIn && <Route path='/mou-moas' element={<MouMoa />} />}
          {isLoggedIn && <Route path='/mou-moas/new' element={<MouMoaAddNew />} />}
          {isLoggedIn && <Route path='/mou-moas/:id' element={<MouMoaSingle />} />}

          {isLoggedIn && <Route path='/ktp-usrs' element={<KtpUsr />} />}
          {isLoggedIn && <Route path='/ktp-usrs/new' element={<KtpUsrAddNew />} />}
          {isLoggedIn && <Route path='/ktp-usrs/:id' element={<KtpUsrSingle />} />}

          {isLoggedIn && <Route path='/mobilities' element={<Mobility />} />}
          {isLoggedIn && <Route path='/mobilities/new' element={<MobilityAddNew />} />}
          {isLoggedIn && <Route path='/mobilities/:id' element={<MobilitySingle />} />}

          {isLoggedIn && <Route path='/backup' element={<Backup/>}/>}
          {isLoggedIn && <Route path='/import' element={<Import/>}/>}
          {!isLoggedIn && <Route path='/' element={<Login/>}/>}
          {!isLoggedIn && <Route path='*' element={<Login/>}/>}
                </Routes>
            </BrowserRouter>
        </div>
    );

}

export default App;

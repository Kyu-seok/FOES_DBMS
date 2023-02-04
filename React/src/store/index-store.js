import { configureStore } from '@reduxjs/toolkit';
import uiReducer from './ui-slice';
import authReducer from './auth-slice';

const store = configureStore({
  reducer: { ui: uiReducer, auth: authReducer }
});

export default store;
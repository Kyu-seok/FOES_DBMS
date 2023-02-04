import { createSlice } from '@reduxjs/toolkit';

const initialAuthState = {
  isLoggedIn: false,
  isAdmin: 0,
  tokenId: '',
  exp: 0 
}

const authSlice = createSlice({
  name: 'auth',
  initialState: initialAuthState,
  reducers: {
    login(state, action) {
      state.tokenId = action.payload[0];
      state.isAdmin = action.payload[1] == 1 ? true : false;
      state.exp = action.payload[2];
      state.isLoggedIn = true;
      localStorage.setItem('token', state.tokenId);
      localStorage.setItem('isAdmin', state.isAdmin == true ? 1 : 0);
      localStorage.setItem('exp', state.exp);
    },
    logout(state) {
      state.tokenId = '';
      state.isLoggedIn = false;
      localStorage.removeItem('token');
      localStorage.removeItem('isAdmin');
      localStorage.removeItem('exp');
    },
  }
})

export const authActions = authSlice.actions;
export default authSlice.reducer;
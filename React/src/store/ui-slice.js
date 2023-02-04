import { createSlice } from '@reduxjs/toolkit';

const initialUiState = { isDarkMode: false }

const uiSlice = createSlice({
  name: 'ui',
  initialState: initialUiState,
  reducers: {
    toggleDarkMode(state) {state.isDarkMode = !state.isDarkMode},
    setDarkMode(state) {state.isDarkMode = true},
    setLightMode(state) {state.isDarkMode = false},
  }
})

export const uiActions = uiSlice.actions;
export default uiSlice.reducer; 
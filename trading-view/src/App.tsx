import React from "react";
import "./App.css";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import Main from "./Main";

const theme = createTheme({
  palette: {
    mode: "light",
    secondary: {
      main: "#FFCD00",
    },
  },
});

function App() {
  return (
      <ThemeProvider theme={theme}>
        <Main></Main>
      </ThemeProvider>
  );
}

export default App;
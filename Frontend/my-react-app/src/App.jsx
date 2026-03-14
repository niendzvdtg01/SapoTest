import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import LoginComponent from "./Component/Login";
function App() {
  return (
    <Routes>
      <Route path="/" element={<LoginComponent />} />
    </Routes>
  )
}

export default App

import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import LoginComponent from "./Component/Login";
import ProducList from "./Component/ProductList";
function App() {
  return (
    <Routes>
      <Route path="/" element={<LoginComponent />} />
      <Route path="/shop" element={<ProducList />} />
    </Routes>
  )
}

export default App

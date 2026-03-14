import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
const LoginComponent = () => {
    const [isLogin, setIsLogin] = useState(true);
    const LOGINURL = "http://localhost:8080/auth/login";
    const REGISTERURL = "http://localhost:8080/user/create_user";
    const naviagate = useNavigate();
    const [form, setForm] = useState({
        username: "",
        email: "",
        password: ""

    })
    const handleChange = (e) => {
        const { name, value } = e.target
        setForm(prev => ({
            ...prev,
            [name]: value
        }))
    }

    const handleSubmit = async () => {
        try {
            const url = isLogin ? LOGINURL : REGISTERURL;
            const res = await axios.post(url, form, {
                withCredentials: true
            });
            alert(res.status);
            const data = await res.data;
            naviagate("/shop");
            console.log(data);
        } catch (error) {
            console.error("Error:", error);
        }
    }
    return (
        <>
            <div className="container d-flex justify-content-center align-items-center vh-100">

                <div className="card shadow p-4" style={{ width: "400px" }}>

                    <h3 className="text-center mb-4">
                        {isLogin ? "Login" : "Register"}
                    </h3>

                    <form>

                        {!isLogin && (
                            <div className="mb-3">
                                <label className="form-label">Email</label>
                                <input
                                    type="email"
                                    className="form-control"
                                    placeholder="Enter email"
                                    name="email"
                                    value={form.email}
                                    onChange={handleChange}
                                />
                            </div>
                        )}

                        <div className="mb-3">
                            <label className="form-label">Username</label>
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Enter username"
                                name="username"
                                value={form.username}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="mb-3">
                            <label className="form-label">Password</label>
                            <input
                                type="password"
                                className="form-control"
                                placeholder="Enter password"
                                name="password"
                                value={form.password}
                                onChange={handleChange}
                            />
                        </div>

                        <button className="btn btn-primary w-100" type="button" onClick={handleSubmit}>
                            {isLogin ? "Login" : "Register"}
                        </button>

                    </form>

                    <div className="text-center mt-3">

                        {isLogin ? (
                            <p>
                                Don't have an account?{" "}
                                <button
                                    className="btn btn-link p-0"
                                    onClick={() => setIsLogin(false)}
                                >
                                    Register
                                </button>
                            </p>
                        ) : (
                            <p>
                                Already have an account?{" "}
                                <button
                                    className="btn btn-link p-0"
                                    onClick={() => setIsLogin(true)}
                                >
                                    Login
                                </button>
                            </p>
                        )}

                    </div>
                </div>
            </div>
        </>
    );
};
export default LoginComponent;
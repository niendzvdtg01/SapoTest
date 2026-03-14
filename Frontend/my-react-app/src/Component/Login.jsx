import React, { useState } from "react";

const LoginComponent = () => {
    const [isLogin, setIsLogin] = useState(true);
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
                                <label className="form-label">Username</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    placeholder="Enter username"
                                />
                            </div>
                        )}

                        <div className="mb-3">
                            <label className="form-label">Email</label>
                            <input
                                type="email"
                                className="form-control"
                                placeholder="Enter email"
                            />
                        </div>

                        <div className="mb-3">
                            <label className="form-label">Password</label>
                            <input
                                type="password"
                                className="form-control"
                                placeholder="Enter password"
                            />
                        </div>

                        {!isLogin && (
                            <div className="mb-3">
                                <label className="form-label">Confirm Password</label>
                                <input
                                    type="password"
                                    className="form-control"
                                    placeholder="Confirm password"
                                />
                            </div>
                        )}

                        <button className="btn btn-primary w-100">
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
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import './Login.css';

function Login({ onLogin }) {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [isLoading, setIsLoading] = useState(false);
  const navigate = useNavigate();

  const API_BASE_URL = "http://localhost:8080";

  const handleLogin = async (e) => {
    e.preventDefault();
    setError("");
    setIsLoading(true);

    try {
      const response = await fetch(`${API_BASE_URL}/api/admin/login`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ email, password }),
      });

      if (!response.ok) {
        if (response.status === 401) throw new Error("Invalid email or password");
        if (response.status === 404) throw new Error("Server endpoint not found");
        throw new Error(`Server responded with status: ${response.status}`);
      }

      const data = await response.json();

      if (data.token) {
        window.localStorage.setItem("jwtToken", data.token);
        const user = { email, token: data.token };
        window.localStorage.setItem("loggedInUser", JSON.stringify(user));
        onLogin(user);
        navigate("/register");
      } else {
        throw new Error("No token received from server");
      }
    } catch (err) {
      setError(err.message || "Login failed. Please try again.");
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="container">
      {/* Title */}
      <h1 className="title">Admin Login</h1>

      {/* Form */}
      <form onSubmit={handleLogin} className="form">
        <div className="form-group">
          <label className="label" htmlFor="email">Email:</label>
          <input
            id="email"
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            className="input"
            disabled={isLoading}
            required
            placeholder="Enter your email"
          />
        </div>

        <div className="form-group">
          <label className="label" htmlFor="password">Password:</label>
          <input
            id="password"
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className="input"
            disabled={isLoading}
            required
            placeholder="Enter your password"
          />
        </div>

        <button
          type="submit"
          className="submit-btn"
          disabled={isLoading}
        >
          {isLoading ? "Logging in..." : "Login"}
        </button>
      </form>

      {/* Error Message */}
      {error && <div className="error-message">{error}</div>}
    </div>
  );
}

export default Login;

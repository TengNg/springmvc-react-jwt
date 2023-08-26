import React from "react";

export default function RegisterPage() {
    return <div className="register-page">
        <div className="register-form-register">
            <h1 className="title">RegisterAccount</h1>

            <form>
                <div>
                    <label htmlFor="first-name" className="form-label">
                        First Name
                    </label>
                    <input
                        id="first-name"
                        className="form-control"
                        type="text"
                        name="first-name"
                    />
                </div>

                <div className="mb-2">
                    <label htmlFor="last-name" className="form-label">
                        Last Name
                    </label>
                    <input
                        id="last-name"
                        className="form-control"
                        type="text"
                        name="last-name"
                    />
                </div>

                <div className="mb-2">
                    <label htmlFor="email" className="form-label">
                        Email
                    </label>
                    <input
                        id="email"
                        className="form-control"
                        type="text"
                        name="email"
                    />
                </div>

                <div className="mb-2">
                    <label htmlFor="phone-number" className="form-label">
                        Phone Number
                    </label>
                    <input
                        id="phong-number"
                        className="form-control"
                        type="text"
                        name="phone-number"
                    />
                </div>

                <div className="mb-2">
                    <label htmlFor="password" className="form-label">
                        Password
                    </label>
                    <input
                        id="password"
                        className="form-control"
                        type="password"
                        name="password"
                    />
                </div>

                <div className="mb-2">
                    <label htmlFor="confirm-password" className="form-label">
                        Confirm Password
                    </label>
                    <input
                        id="confirm-password"
                        className="form-control"
                        type="password"
                        name="comfirmPassword"
                    />
                </div>

                <button type="submit" className="submit-btn">
                    Register
                </button>
            </form>
        </div>
    </div>
}

import React, { useState } from "react";
import { signUp } from "../service/api";

export default function UserLogin() {
    const [email,setEmail] = useState();
    const [password,setPassword] = useState();

    const LoginSubmit = async (e) => {
        e.preventDefault();
        // 로그인
        const response = await signUp({ email, password });
        if (response.success) {
            console.log('로그인 성공:', response.token);
        } else {
            // 실패 처리
            console.error('로그인 실패');
        }
    };
    
    return (
        <div>
            <form onSubmit={LoginSubmit}>
                <div>
                    id :
                    <input type="text" value={email} onChange={(e) => setEmail(e.target.value)}/>
                </div>
                <div>
                    pass :
                    <input type="password" value={password} onChange={(e) => setPassword(e.target.value)}/>
                </div>
                <button type="submit">로그인</button>
            </form>
        </div>
    )
}
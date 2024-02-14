import React, { useState } from "react";

export default function UserLogin() {
    const [userId,setUserId] = useState();
    const [userPass,setUserPass] = useState();

    const Login = () =>{
        const memberId=document.getElementById('memberId').value;
        const memberPassword=document.getElementById('memberPassword').value;
        console.log('로그인'+memberId);
        console.log('로그인'+memberPassword);
    }
    
    return (
        <div>
            <div>
                id :
                <input type="text" id="memberId"/>
            </div>
            <div>
                pass :
                <input type="password" id="memberPassword" />
            </div>

            <button onClick={Login}>로그인</button>
        </div>
    )
}
import {useState} from "react";
import {signUp} from "../service/api.js";
export default function UserJoin() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [nickname, setNickname] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        // 회원가입 로직 구현
        const response = await signUp({ email, password, nickname });
        if (response.success) {
            console.log('회원가입 성공:', response.token);
        } else {
            // 실패 처리
            console.error('회원가입 실패');
        }
    };
    return (
        <div>
            <h2>회원가입</h2>
            <form onSubmit={handleSubmit}>
                <input
                    type="email"
                    placeholder="이메일"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                />
                <input
                    type="password"
                    placeholder="패스워드"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
                <input
                    type="text"
                    placeholder="닉네임"
                    value={nickname}
                    onChange={(e) => setNickname(e.target.value)}
                />
                <button type="submit">회원가입</button>
            </form>
        </div>
    )
}
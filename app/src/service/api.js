export const signUp = async ({ email, password, nickname }) => {
    try {
        const response = await fetch('/api/users/join', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                memberId: email,
                memberPassword: password,
                memberNickname: nickname,
            }),
        });

        if (!response.ok) {
            throw new Error('Signup request failed');
        }

        const data = await response.json();
        return { success: true, token: 'fake-jwt-token'};
    } catch (error) {
        console.error(error);
        return { success: false, message: error.message };
    }
};
import axios from 'axios';

class AuthService {
    async login(username, password) {
        try {
            const resp = await axios.post('/api/auth/login', {
                username: username,
                password: password
            });
            localStorage.setItem("BearGoAccessToken", resp.data);
            console.log(resp.data);
            return true;
        } catch (error) {
            this.logout();
            console.log(error);
            return false;
        }
    }

    logout() {
        localStorage.removeItem("BearGoAccessToken");
        delete axios.defaults.headers.common["Authorization"];
    }

    setAxiosAuthHeader() {
        axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem('BearGoAccessToken')}`;
    }
}

export default new AuthService();
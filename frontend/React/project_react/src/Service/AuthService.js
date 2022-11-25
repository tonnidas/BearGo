import axios from 'axios';

class AuthService {
    login(username, password) {
        axios.post('/api/auth/login', {
            username: username,
            password: password
        })
        .then(function (response) {
            localStorage.setItem("BearGoAccessToken", response.data);
            console.log(response.data);
            alert('Login succeed');
        })
        .catch(function (error) {
            console.log(error);
        });
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
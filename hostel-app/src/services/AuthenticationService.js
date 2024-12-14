import http from "@/services/external-api/http-common";

class AuthenticationService {

    login(credentials) {
        return http.post(`/users/login`, credentials, { headers: { 'Content-Type': 'application/json' } });
    }

    signup(payload) {
        return http.post(`/users/register`, payload,  { headers: { 'Content-Type': 'application/json' } });
    }
}

export default new AuthenticationService();

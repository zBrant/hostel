import http from "@/services/external-api/http-common";

class AuthenticationService {

    login(id) {
        return http.get(`/game/${id}`);
    }

    signup(payload) {
        return http.post(`/api/v1/user`, payload);
    }
}

export default new AuthenticationService();

import http from "@/services/external-api/http-common";

class RoomService {

    sendRoom(payload) {
        return http.post(`/rooms`, payload, { headers: { 'Content-Type': 'application/json' } });
    }

    sendPhoto({ photo, userId }) {
        const formData = new FormData()
        formData.append('photo', photo)
        return http.post(`/rooms/${userId}/photo`, formData, { headers: { 'Content-Type': 'multipart/form-data' } });
    }

    getAll({ filter, currentPage, itemsPerPage }){
        let params = `?page=${currentPage}&size=${itemsPerPage}`

        if (filter) params += `&city=${filter}`

        return http.get(`/rooms${params}`);
    }
}

export default new RoomService();

import http from "../http-common";
class DataService {
  getAll() {
    return http.get("/get");
  }
  get(id) {
    return http.get(`/get/${id}`);
  }
  create(data) {
    return http.post("/create", data);
  }
  update(id, data) {
    return http.put(`/update/${id}`, data);
  }
  delete(id) {
    return http.delete(`/delete/${id}`);
  }
  deleteAll() {
    return http.delete(`/delete`);
  }

}
export default new DataService();
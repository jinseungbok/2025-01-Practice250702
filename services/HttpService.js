import axios from "axios";
axios.defaults.baseURL = "http://localhost:8080/api";

// promise 객체에만 async, await 사용 가능
class HttpService {
  async save(jsonBody) {
    const res = await axios.post("/memo", jsonBody);
    return res.data;
  }
  async findAll(params) {
    // items 역할, 보낸 이유
    // 쿼리스트링
    const res = await axios.get("/memo", { params });
    return res.data;
  }
  async findById(id) {
    const res = await axios.get(`/memo/${id}`);
    return res.data;
  }
  async modify(jsonBody) {
    const res = await axios.put('/memo', jsonBody);
    return res.data;
  }
  async deleteById(id) {
    const res = await axios.delete('/memo?id=${id}');
    return res.data;
  }
}

export default new HttpService();

import axios from "axios";
const ACOPIO_URL = "http://localhost:8080/acopio";

class AcopioService {

    uploadData(file) {
        return axios.post(ACOPIO_URL + "/uploadklsleche", file);
    }

}
export default new AcopioService();
import axios from "axios";
const CALIDAD_URL = "http://localhost:8080/calidad";

class CalidadService {
    
        uploadData(file) {
            return axios.post(CALIDAD_URL + "/uploadcalidadleche", file);
        }
    
}
export default new CalidadService();
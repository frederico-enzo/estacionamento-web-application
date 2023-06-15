import { Marca } from "@/Model/Marca";
import axios, { AxiosInstance } from "axios";

export class MarcaClient{

    private axiosClient: AxiosInstance;

    constructor() {
        this.axiosClient = axios.create({
            baseURL: 'http://localhost:8080/api/marca',
            headers: {'Content-type' : 'application/json'}
        });
    }

    public async findById(id: number) : Promise<Marca> {
        try {
            return (await this.axiosClient.get<Marca>(`/${id}`)).data
        } catch (error:any) {
            return Promise.reject(error.response)
        }
    }

    public async findAll() : Promise<Arr> {
        try {
            return (await this.axiosClient.get<Marca>(`/lista`)).data
        } catch (error:any) {
            return Promise.reject(error.response)
        }
    }


}
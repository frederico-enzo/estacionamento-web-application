import { Marca } from "@/Model/Marca";
import axios, { AxiosInstance } from "axios";

export class MarcaClient{

    private axiosClient: AxiosInstance;

    constructor() {
        this.axiosClient = axios.create({
            baseURL: 'http://localhost:9000/api/marca',
            headers: {'Content-type' : 'application/json'}
        });
    }

    public async findById(id: number) : Promise<Marca> {
        try {
            return (await this.axiosClient.get<Marca>(`/${id}`)).data
        } catch (error:any) {
            return Promise.reject(error.response);
        }
    }

    public async ativos(): Promise<Marca[]>{
        try {
            return( await this.axiosClient.get<Marca[]>())
        }
    }

    public async listar(): Promise<Marca[]> {
        try {
            return (await this.axiosClient.get<Marca[]>('/lista')).data;
        } catch (error: any) {
            return Promise.reject(error.response);
        }
    }

    public async newMarca(marca: Marca): Promise<void> {
        try {
            return (await this.axiosClient.post('/', marca));
        } catch (error: any) {
            return Promise.reject(error.response);
        }
    }

    public async upDate(id: Number, marca:Marca): Promise<void>{
        try{
            return(await this.axiosClient.put(`/${marca.id}`, marca)).data;
        } catch(error: any) {
            return Promise.reject(error.response);
        }
    }

    public async deletar(id:Number): Promise<void>{
        try{
            return(await this.axiosClient.delete(`${id}`)).data;
        } catch (error: any){
           return Promise.reject(error.response);
        }
    }

}
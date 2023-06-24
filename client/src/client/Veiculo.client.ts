import { Veiculo } from "@/Model/Veiculo";
import axios, { AxiosInstance } from "axios";

export class VeiculoCliente{

    private axiosClient: AxiosInstance;
    
    constructor() {
        this.axiosClient = axios.create({
            baseURL: 'http://localhost:8080/api/veiculo',
            headers: {'Content-type' : 'application/json'}
        });
    }

    public async findById(id: number) : Promise<Veiculo> {
        try{
            return ( await this.axiosClient.get<Veiculo>(`${id}`)).data;
        } catch (error:any){
            return Promise.reject(error.response);
        }
    }

    public async ativos() : Promise<Veiculo[]>{
        try{
            return ( await this.axiosClient.get<Veiculo[]>('/ativos')).data;
        } catch (error:any){
            return Promise.reject(error.response);
        }
    }

    public async findAll(): Promise<Veiculo[]>{
        try{
            return( await this.axiosClient.get<Veiculo[]>('/lista')).data;
        } catch (error:any){
            return Promise.reject(error.response);
        }
    }

    public async newVeiculo(veiculo : Veiculo) : Promise<void>{
        try{
            return (await this.axiosClient.post('', veiculo));
        } catch(error: any){
            return Promise.reject(error.response);
        }
    }
    
    public async upDate (id: Number, veiculo: Veiculo):Promise<void>{
        try{
            return(await this.axiosClient.put(`/${veiculo.id}`, veiculo)).data;
        } catch ( error:any){
            return Promise.reject(error.response);
        }
    }
    public async excluir(id: number): Promise<string> {
        try {
            return (await this.axiosClient.delete<string>(`/${id}`)).data
        } catch (error:any) {
            return Promise.reject(error.response)
        }
    }
}
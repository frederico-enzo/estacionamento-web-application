import { Configuracao } from "@/Model/Configuracao";
import axios, { AxiosInstance } from "axios";

export class ConfiguracaoClient{

    private axiosClient: AxiosInstance;

    constructor() {
        this.axiosClient = axios.create({
            baseURL: 'http://localhost:8080/api/configuracao',
            headers: {'Content-type' : 'application/json'}
        });
    }

    public async findById(id: number) : Promise<Configuracao> {
        try {
            return (await this.axiosClient.get<Configuracao>(`/${id}`)).data
        } catch (error:any) {
            return Promise.reject(error.response);
        }
    }

    public async findAll(): Promise<Configuracao[]> {
        try {
            return (await this.axiosClient.get<Configuracao[]>(`/lista`)).data
        } catch (error:any) {
            return Promise.reject(error.response)
        }
    }
    public async ativos(): Promise<Configuracao[]>{
        try {
            return( await this.axiosClient.get<Configuracao[]>('/ativos')).data
        } catch (error:any){
            return Promise.reject(error.response);
        }
    }

 

    public async newMarca(configuracao: Configuracao): Promise<void> {
        try {
            return (await this.axiosClient.post('', Configuracao));
        } catch (error: any) {
            return Promise.reject(error.response);
        }
    }

    public async upDate(id: Number, configuracao:Configuracao): Promise<void>{
        try{
            return(await this.axiosClient.put(`/${configuracao.id}`, configuracao)).data;
        } catch(error: any) {
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
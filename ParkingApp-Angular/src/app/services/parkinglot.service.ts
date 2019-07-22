import { ParkingLot } from './../models/parkingLot';
import { ConfigService } from './../Config/config.service';
import { Injectable, Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Http ,HttpModule} from '@angular/http';
import {map} from 'rxjs/operators';

@Injectable()
export class ParkingLotService{

  constructor(private http: HttpClient, private configService : ConfigService) { 
    this.baseUrl = configService._apiURI;
    this.parkingLotUrl = this.baseUrl + "parkinglot/";
  }

  baseUrl;
  parkingLotUrl;


  public getTownByCoordinates(latitude: number, longitude:number){
    return this.http.get("https://nominatim.openstreetmap.org/reverse?format=xml&lat=" + latitude + "&lon=" +longitude +"&zoom=18&addressdetails=1", {responseType: 'text'});
  } 
  
  public getParkingLotsByTown(town: string): Observable<ParkingLot[]>{
    return this.http.get<ParkingLot[]>(this.parkingLotUrl + "getbytown/" + town);
  }

  public getParkingLotsByLocation(location: string): Observable<ParkingLot[]>{
    return this.http.get<ParkingLot[]>(this.parkingLotUrl + "getbyLocation/" + location);
  }

  public getParkingLotByLocation(location: string): Observable<ParkingLot>{
    return this.http.get<ParkingLot>(this.parkingLotUrl + "getPLotbyLocation/" + location);
  }

  public getParkingLots(): Observable<ParkingLot[]>{
    return this.http.get<ParkingLot[]>(this.parkingLotUrl + "getAll");
  }

  public deleteParkinLot(location: string){
    return this.http.delete(this.parkingLotUrl + "delete/" + location,{responseType: 'text'}); 
  }

  public addParkingLot(name: string,town: string,latitude: string,longitude: string, nr_spots: number,location:string,price:number){
    return this.http.get(this.parkingLotUrl+"add/"+ name + "/" +town+"/" +latitude+"/" +longitude+"/" +nr_spots+"/" +location+"/" +price,{responseType: 'text'})
  }

  public updateParkingLot(name: string,town: string,latitude: string,longitude: string, nr_spots: number,location:string,price:number){
    return this.http.get(this.parkingLotUrl+"update/"+ name + "/" +town+"/" +latitude+"/" +longitude+"/" +nr_spots+"/" +location+"/" +price,{responseType: 'text'})
  }

  public getTownLocation(api: string){
    return this.http.get(api);
  }
}
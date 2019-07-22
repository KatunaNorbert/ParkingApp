import { Reservation } from './../models/reservation';
import { ParkingLot } from './../models/parkingLot';
import { ConfigService } from './../Config/config.service';
import { Injectable, Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
;

@Injectable()
export class ReservationService{

  constructor(private http: HttpClient, private configService : ConfigService) { 
    this.baseUrl = configService._apiURI;
    this.ReservationUrl = this.baseUrl + "reservation/";
  }

  baseUrl;
  ReservationUrl;

  public makePayment(sum: number){
    return this.http.post(this.baseUrl+"paypal/make/payment/"+sum,{})
  }

  public createPayment(paymentId:string,payerId:string){
    return this.http.post(this.baseUrl+"paypal/complete/payment?paymentId="+paymentId+"&PayerID="+payerId,{})
  }

  public saveUserData(email: string){
    return this.http.get(this.ReservationUrl + "saveUser/" + email, {responseType: 'text'});
  }

  public saveParkingLot(location: string){
    return this.http.get(this.ReservationUrl + "saveParkingLot/" + location, {responseType: 'text'});
  }

  /*public makeReservation(res: Reservation){
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };
    return this.http.post<Reservation>(this.ReservationUrl+"add",JSON.stringify(res),httpOptions);
  }*/
  public makeReservation(start: String, end :String, email :String, location:String){
    return this.http.get(this.ReservationUrl+"add/"+start+"/"+end+"/"+email+"/"+location,{responseType: 'text'});
  }

  public extendReservation(end: string,id:number){
    return this.http.get(this.ReservationUrl+"extendReservation/"+end+"/"+id,{responseType: 'text'});
  }

  public sendEmail(email: string,qrCode: string,location: string,start:string,end:string){
    return this.http.get(this.ReservationUrl+"sendEmail/"+email+"/"+qrCode+"/"+location+"/"+start+"/"+end,{responseType: 'text'});
  }

  public deleteReservation(id: Number){
    return this.http.delete(this.ReservationUrl+"delete/"+id,{responseType: 'text'});
  }

  public deleteExpiredReservation(date: String){
    return this.http.delete(this.ReservationUrl+"deleteExpired/"+date,{responseType: 'text'});
  }

  public getUserReservations(email: String): Observable<Reservation[]>{
    return this.http.get<Reservation[]>(this.ReservationUrl + "getUserReservations/" + email);
  }

  public getReservationsFromParkingLot(location: String): Observable<Reservation[]>{
    return this.http.get<Reservation[]>(this.ReservationUrl + "getReservationsFromParkingLot/" + location);
  }

  public getNumberOfReservedSpots(start: String,end: String){
    return this.http.get(this.ReservationUrl+"getNumberOfReservedSpots/"+start+"/"+end,{responseType: 'text'});
  }
 
}
import { ParkingLot } from 'src/app/models/parkingLot';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ReservationService } from 'src/app/services/reservation.service';
import { Reservation } from 'src/app/models/reservation';

@Component({
  selector: 'app-parking-lot-charts',
  templateUrl: './parking-lot-charts.component.html',
  styleUrls: ['./parking-lot-charts.component.css']
})
export class ParkingLotChartsComponent implements OnInit {

  reservations: Reservation[];
  currentDate= new Date();

  type="BarChart"
  data1 = []
  date1
  date2
  minutes
  currentPrice
  columnNames1 = ['Dates', 'NumberOfReservations']
  days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday']

  data2 = []
  columnNames2 = ['Dates', 'Price']

  public pLots: ParkingLot[];
  constructor(private reservationService:ReservationService,private router: Router) { }

  getPrice(d1: Date,d2: Date,price){
    this.date1= new Date(d1);
    this.date2= new Date(d2);
    var diff =(this.date2.getTime() - this.date1.getTime()) / 1000;
    diff /= 60;
    this.minutes= Math.abs(Math.round(diff));
    this.currentPrice=((this.minutes/60)*price);
    return this.currentPrice;
  }

  ngOnInit() {
    if(localStorage.getItem("loggedIn")!=null){
      this.reservationService.getReservationsFromParkingLot(localStorage.getItem("location")).subscribe(data =>{
        this.reservations=data;
        let lastDate=new Date();
        let number=0;
        let price=0;
        for(let i=0;i<7;i++){
          this.reservations.map(r=>{
            let date=new Date(r.start);
            if(date.getDay()==lastDate.getDay()){
              number++;
              price+=this.getPrice(new Date(r.start),new Date(r.end),r.pLot.price);
            }
          })
          this.data1.push([this.days[lastDate.getDay()],number]);
          this.data2.push([this.days[lastDate.getDay()],price]);
          price=0;
          number=0;
          lastDate.setDate(lastDate.getDate()+1);
        }
      })
    }else{
      this.router.navigate(['/login']);
      }
  }

}

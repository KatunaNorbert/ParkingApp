import { element } from 'protractor';
import { Router } from '@angular/router';
import { UserService } from './../services/user.service';
import { ParkingLot } from './../models/parkingLot';
import { Component, OnInit } from '@angular/core';
import { ParkingLotService } from '../services/parkinglot.service';
import { ReservationService } from '../services/reservation.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public parkingLots=[];
  public logged=false;
  d: Date=new Date;
  lat : number;
  long : number;
  activeId="nearby";
  public loading = false;

  constructor(private datePipe: DatePipe,private resService: ReservationService,private ParkLotService : ParkingLotService,private userService: UserService,private router: Router) { }

  searchSpot(form) {
      this.ParkLotService.getParkingLotsByTown(form.value.search).subscribe(data => this.parkingLots=data);
  }

  verifyLog(location: string){
    console.log("here");
    if(this.logged){
        localStorage.setItem('location',location);
          this.router.navigate(['/parkingSpot']);
    }else{
      this.router.navigate(['/login']);
    }
  }

  getNearby(){
    let currentActive=document.getElementById(this.activeId);
    let current=document.getElementById('nearby');
    this.activeId="nearby";
    currentActive.className="col-xl-6 col-md-6 col-sm-12";
    current.className='col-xl-6 col-md-6 col-sm-12 active';
    this.ParkLotService.getTownByCoordinates(this.lat,this.long).subscribe(data=>{
      var parser = new DOMParser();
      var xmlDoc = parser.parseFromString(data,"text/xml");
      var city=xmlDoc.getElementsByTagName("city")[0].childNodes[0].nodeValue;
      this.ParkLotService.getParkingLotsByTown(city).subscribe(data =>{
        this.parkingLots=data;
        this.loading=false;
      });
    });

  }

  getAll(){
    let currentActive=document.getElementById(this.activeId);
    let all=document.getElementById('all');
    this.activeId="all";
    currentActive.className="col-xl-6 col-md-6 col-sm-12";
    all.className='col-xl-6 col-md-6 col-sm-12 active';
    this.ParkLotService.getParkingLots().subscribe(data => this.parkingLots=data);
  }

  ngOnInit() {
    this.loading=true;
    localStorage.setItem("extend","false");
    navigator.geolocation.getCurrentPosition((position) =>{
      this.lat=position.coords.latitude;
      this.long=position.coords.longitude;
      this.ParkLotService.getTownByCoordinates(this.lat,this.long).subscribe(data=>{
        var parser = new DOMParser();
        var xmlDoc = parser.parseFromString(data,"text/xml");
        var city=xmlDoc.getElementsByTagName("city")[0].childNodes[0].nodeValue;
        this.ParkLotService.getParkingLotsByTown(city).subscribe(data =>{
          this.parkingLots=data;
          this.loading=false;
        });
      });
    });
    this.ParkLotService.getParkingLotsByTown("Cluj-Napoca").subscribe(data =>{
      this.parkingLots=data;
      this.loading=false;
    });
   if(localStorage.getItem('loggedIn')!=null){
      this.logged=true;
    }       
  }

}

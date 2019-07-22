import { ReservationService } from './../services/reservation.service';
import { ParkingLotService } from './../services/parkinglot.service';
import { DoneModalComponent } from './../done-modal/done-modal.component';
import { ParkingLot } from './../models/parkingLot';
import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

declare let L;

@Component({
  selector: 'app-parking-spot',
  templateUrl: './parking-spot.component.html',
  styleUrls: ['./parking-spot.component.css']
})
export class ParkingSpotComponent implements OnInit {
  
  private parkingLot;

  timepickerVisible = false;
  startTime: Date=new Date;
  endTime: Date=new Date;
  endDate: Date=new Date;
  startDate: Date= new Date;
  currentDate: Date
  resDate2: string;
  resDate1: string;
  maxDate: Date;
  invalid: boolean=false;
  minutes: number;
  price: number;
  currentPrice: number=0;
  freeSpots:number=0;
  spotNumber:number;
  re: ReservationService;
  date1: Date=new Date;
  date2: Date= new Date;
  qrValue: string;
  socket: any;
  message: string="socket";
  public loading = true;
  extend=false;

  constructor(private resService: ReservationService,private parkingLotService:ParkingLotService,private datePipe: DatePipe,private router: Router,private modalService: NgbModal,private activatedRoute:ActivatedRoute) { 
    this.loading=true;
    this.currentDate = new Date();
    this.maxDate = new Date();
    this.currentDate.setDate(this.currentDate.getDate());
    this.maxDate.setDate(this.maxDate.getDate() + 7);
    activatedRoute.queryParams.subscribe(params=>{
      if(params["paymentId"]!=undefined && params["PayerID"]!=undefined){
        this.resService.createPayment(params["paymentId"],params["PayerID"]).subscribe(
          (res:any[])=>{
            if(res[0]==='success'){
              if(localStorage.getItem("extend")=="false"){
                let reservation:any=JSON.parse(localStorage.getItem('reservation'));
                this.resService.makeReservation(reservation.start,reservation.end,localStorage.getItem("loggedIn"),localStorage.getItem("location")).subscribe((data) =>{
                  this.sendMessage();
                  this.qrValue=reservation.start+"111"+reservation.end+"111"+localStorage.getItem("location")+"111"+localStorage.getItem("loggedIn");
                  this.resService.sendEmail(localStorage.getItem("loggedIn"),this.qrValue,localStorage.getItem("location"),reservation.start,reservation.end).subscribe(data=>{})
                  const modalRef = this.modalService.open(DoneModalComponent);
                  this.router.navigate(['/home']);
                })
              }
              else{
                this.resService.extendReservation(localStorage.getItem("end"),parseInt(localStorage.getItem('id'),10)).subscribe(data =>{
                  if(data=="done"){
                    const modalRef = this.modalService.open(DoneModalComponent);
                    this.router.navigate(['/home']);
                  }
                });
              }
            }
          },
          err=>{console.log(err)}
        )
      }
    })
  }

  getTimeDifference(d1: Date,d2: Date){
    this.date1= new Date(this.startDate);
    this.date1.setHours(d1.getHours());
    this.date1.setMinutes(d1.getMinutes());
    this.date2= new Date(this.endDate);
    this.date2.setHours(d2.getHours());
    this.date2.setMinutes(d2.getMinutes());
    var diff =(this.date2.getTime() - this.date1.getTime()) / 1000;
    diff /= 60;
    this.minutes= Math.abs(Math.round(diff));
    this.currentPrice=((this.minutes/60)*this.price);
    return this.currentPrice;
  }

  setParkingLot(p: ParkingLot){
    this.parkingLot=p;
  }

  reserve(form){
    if(this.startDate.getDate()==this.endDate.getDate() && this.startTime>=this.endTime){
      this.invalid=true;
    }else{
    localStorage.setItem("extend","false");
    this.invalid=false;
    let reservation:any={
        id:0,
        start:this.datePipe.transform(this.date1, 'yyyy-MM-dd hh:mm:ss'),
        end:this.datePipe.transform(this.date2, 'yyyy-MM-dd hh:mm:ss'),
        pLot:null,
        user:null
    }
    localStorage.setItem('reservation',JSON.stringify(reservation));
    this.resService.makePayment(this.price).subscribe((data:any)=>{
      window.location.href=data.redirect_url;
    })
  }
  }

  extendRes(){
    this.resService.makePayment(this.price).subscribe((data:any)=>{
       localStorage.setItem("end",this.datePipe.transform(this.date2, 'yyyy-MM-dd hh:mm:ss'));
       window.location.href=data.redirect_url;
    })
  }

  setArreavinMinutes(value: Date){
    console.log(value.getTimezoneOffset());
  }

  saveAreavingDate(value: Date): void {
    this.startDate = value;
    this.resDate1=this.datePipe.transform(this.startDate, 'yyyy-MM-dd');
  }

  saveLeavingDate(value: Date): void {
    this.endDate = value;
    this.resDate2=this.datePipe.transform(this.endDate, 'yyyy-MM-dd');
  }

  updatePrice(){
    this.currentPrice=this.getTimeDifference(this.startTime,this.endTime);
    this.resService.getNumberOfReservedSpots(this.resDate1+" "+this.datePipe.transform(this.startTime, 'hh:mm:ss'),this.resDate2+" "+this.datePipe.transform(this.endTime, 'hh:mm:ss')).subscribe((data) =>{
      this.freeSpots=this.spotNumber-parseInt(data, 10);
    })
  }


  sendMessage() {
    var message =this.message;
    this.socket.send(JSON.stringify({message: message}));
    message= "";  
  }

  ngOnInit() {
    this.loading=true;
    if(localStorage.getItem('loggedIn')!=null){
        if(localStorage.getItem("extend")=="true"){
          this.extend=true;
          this.date1=new Date(localStorage.getItem('end'));
          this.startDate=new Date(this.date1);
          this.endDate=new Date(this.date1);
          this.startTime.setHours(this.date1.getHours());
          this.startTime.setMinutes(this.date1.getMinutes());
          this.endTime.setHours(this.date1.getHours());
          this.endTime.setMinutes(this.date1.getMinutes());
        }
        this.parkingLotService.getParkingLotByLocation(localStorage.getItem("location")).subscribe((data: ParkingLot) => {
          setTimeout(()=>{
            this.loading=false;
          }, 5000)
          this.setParkingLot(data);
          this.spotNumber=data.spot_number;
          this.price=data.price;
          var lat;
          var lon;
          const map = L.map('map').setView([this.parkingLot.latitude, this.parkingLot.longitude], 13);
          L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
              attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
          }).addTo(map);
          /*navigator.geolocation.getCurrentPosition(function(position) {
            lat=position.coords.latitude;
            lon=position.coords.longitude;
            L.marker([position.coords.latitude, position.coords.longitude]).addTo(map)
            .bindPopup("You")
            .openPopup();
          });*/     
          L.marker([this.parkingLot.latitude, this.parkingLot.longitude]).addTo(map)
              .bindPopup(this.parkingLot.name)
              .openPopup();     
        });
    }else{
      this.router.navigate(['/login']);
      this.loading=false;
    }
    this.socket = new WebSocket("ws://localhost:8080/ParkingApp/atpendpoint");
    this.socket.onopen = function (event) {
        displayMessage("WebSocket connection open");
    };

    this.socket.onclose = function (event) {
        displayMessage("WebSocket connection closed");
    };

    this.socket.onmessage =(event) =>{
      this.resService.getNumberOfReservedSpots(this.datePipe.transform(this.date1, 'yyyy-MM-dd hh:mm:ss'),this.datePipe.transform(this.date2, 'yyyy-MM-dd hh:mm:ss')).subscribe((data) =>{
        this.freeSpots=this.spotNumber-parseInt(data, 10);
      })
    }

    function displayMessage(message){
      console.log(message);
    }
  }

}

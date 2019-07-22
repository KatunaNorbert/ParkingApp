import { DoneModalComponent } from './../done-modal/done-modal.component';
import { UserService } from './../services/user.service';
import { User } from './../models/user';
import { Component, OnInit, Input } from '@angular/core';
import {Router} from '@angular/router';
import { ReservationService } from '../services/reservation.service';
import { NgbActiveModal,NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  template: `
    <div class="modal-header">
      <h4 class="modal-title">Ticket</h4>
      <button type="button" class="close" aria-label="Close" (click)="activeModal.dismiss('Cross click')">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
    <div class="modal-body">
      <ngx-qrcode qrc-version = "18" qrc-class = "test-class" (click)="showQR(name)" [qrc-element-type]="'img'" [qrc-value] = "name"></ngx-qrcode>
    </div>
    <div class="modal-footer">
      <button type="button" class="btn btn-outline-dark" (click)="activeModal.close('Close click')">Close</button>
    </div>
  `,
  styleUrls: ['./user-informations.component.css'],
})
export class NgbdModalContent {
  @Input() name;

  showQR(name:any){
    console.log(name);
  }

  constructor(public activeModal: NgbActiveModal) {
  }
}


@Component({
  selector: 'app-user-informations',
  templateUrl: './user-informations.component.html',
  styleUrls: ['./user-informations.component.css'],
})
export class UserInformationsComponent implements OnInit {

  user: User=new User();
  public reservations=[];
  public loading = false;
  socket: any;
  message: string="socket";

  constructor(private userService: UserService,private router: Router,private resService:ReservationService,private modalService: NgbModal) {
  
   }

   open(start,end,location) {
    const modalRef = this.modalService.open(NgbdModalContent);
    modalRef.componentInstance.name = "http://172.20.10.2/ParkingApp/reservation/verify/"+start+"/"+end+"/"+location+"/"+localStorage.getItem("loggedIn");
  }
   
  deleteReservation(id){
    this.resService.deleteReservation(id).subscribe(data=>{
      const modalRef = this.modalService.open(DoneModalComponent);
      this.resService.getUserReservations(localStorage.getItem("loggedIn")).subscribe(data =>{
        this.reservations=data;
        this.sendMessage();
        location.reload();
      });
    });
  }
  
  extendReservation(location,endDate,id){
    localStorage.setItem('extend','true');
    localStorage.setItem('end',endDate);
    localStorage.setItem('location',location);
    localStorage.setItem('id',id);
    this.router.navigate(['/parkingSpot']);
  }

  edit(){
    this.router.navigate(['/editProfile']);
  }

  sendMessage() {
    var message =this.message;
    this.socket.send(JSON.stringify({message: message}));
    message= "";  
  }


  ngOnInit() {
        this.loading=true;
        localStorage.setItem("extend","false");
        if(localStorage.getItem('loggedIn')!=null){
        this.userService.getUser(localStorage.getItem('loggedIn')).subscribe((data: User) => {
          this.user.name=data.name;
          this.user.surname=data.surname;
          this.user.email=data.email;
          this.user.password=data.password;
          this.user.id=data.id;
        });
        this.socket = new WebSocket("ws://localhost:8080/ParkingApp/atpendpoint");
        this.socket.onopen = function (event) {
            displayMessage("WebSocket connection open");
        };

        this.socket.onclose = function (event) {
            displayMessage("WebSocket connection closed");
        };

        this.socket.onmessage =(event) =>{
          console.log("sooockeet");
        }
        this.resService.getUserReservations(localStorage.getItem("loggedIn")).subscribe(data =>{
          if(data!=null){
            this.reservations=data;
          }
          this.loading=false;
        });
      }else{
        this.router.navigate(['/login']); 
      }
      
      function displayMessage(message){
        console.log(message);
      }
  }

}

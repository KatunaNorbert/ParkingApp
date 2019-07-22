import { ParkingLotService } from './services/parkinglot.service';
import { ConfigService } from './Config/config.service';
import { UserService } from './services/user.service';
import { UserInformationsComponent, NgbdModalContent } from './user-informations/user-informations.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {RouterModule } from '@angular/router';
import {HttpClientModule } from '@angular/common/http'
import { HttpModule } from '@angular/http'
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BsDatepickerModule } from 'ngx-bootstrap';
import { TimepickerModule } from 'ngx-bootstrap/timepicker';
import { PopoverModule } from 'ngx-bootstrap/popover';
import {DatePipe} from '@angular/common';
import { NgxQRCodeModule } from 'ngx-qrcode2';
import { NgxLoadingModule,ngxLoadingAnimationTypes } from 'ngx-loading';


import { AppComponent } from './app.component';
import { LoginComponent } from './user/login/login.component';
import { RegisterComponent } from './user/register/register.component';
import { HomeComponent } from './home/home.component';
import { UserComponent } from './user/user.component';
import { ParkingSpotComponent } from './parking-spot/parking-spot.component';
import { ReservationService } from './services/reservation.service';
import { HeaderComponent } from './header/header.component';
import { AdminHomeComponent } from './admin/admin-home/admin-home.component';
import { AdminHeaderComponent } from './admin/admin-header/admin-header.component';
import { EditUserComponent } from './admin/edit-user/edit-user.component';
import { EditParkingLotComponent } from './admin/edit-parking-lot/edit-parking-lot.component';
import { NgbModule,NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { DoneModalComponent } from './done-modal/done-modal.component';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import { SearchParkingLotComponent } from './admin/search-parking-lot/search-parking-lot.component';
import { AddParkingLotComponent } from './admin/add-parking-lot/add-parking-lot.component';
import { ParkingLotChartsComponent } from './admin/parking-lot-charts/parking-lot-charts.component';
import { GoogleChartsModule } from 'angular-google-charts';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    UserInformationsComponent,
    UserComponent,
    ParkingSpotComponent,
    HeaderComponent,
    AdminHomeComponent,
    AdminHeaderComponent,
    EditUserComponent,
    EditParkingLotComponent,
    NgbdModalContent,
    DoneModalComponent,
    EditProfileComponent,
    SearchParkingLotComponent,
    AddParkingLotComponent,
    ParkingLotChartsComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    NgbModule.forRoot(),
    HttpModule,
    FormsModule,
    NgxQRCodeModule,
    BsDatepickerModule.forRoot(),
    TimepickerModule.forRoot(),
    PopoverModule.forRoot(),
    GoogleChartsModule.forRoot(),
    NgxLoadingModule.forRoot({
      animationType: ngxLoadingAnimationTypes.circleSwish,
        backdropBackgroundColour: 'rgba(51, 110, 123, 1)', 
        backdropBorderRadius: '4px',
        primaryColour: '#ffffff', 
        secondaryColour: '#ffffff', 
        tertiaryColour: '#ffffff'
    }),
    ReactiveFormsModule,
    RouterModule.forRoot([
      {
        path: 'login',
        component: LoginComponent
      },
      {
        path: 'register',
        component: RegisterComponent
      },
      {
        path: 'myprofile',
        component: UserInformationsComponent
      },
      {
        path: 'user',
        component: UserComponent
      },
      {
        path: 'parkingSpot',
        component: ParkingSpotComponent
      },
      {
        path: 'adminhome',
        component: AdminHomeComponent
      },
      {
        path: 'editUser',
        component: EditUserComponent
      },
      {
        path: 'editProfile',
        component: EditProfileComponent
      },
      {
        path: 'editParkingLot',
        component: EditParkingLotComponent
      },
      {
        path: 'searchParkingLot',
        component: SearchParkingLotComponent
      },
      {
        path: 'addParkingLot',
        component: AddParkingLotComponent
      },
      {
        path: 'charts',
        component: ParkingLotChartsComponent
      },
      {
        path: '**',
        component: HomeComponent
      },
      {
        path: '',
        component: HomeComponent
      }
    ]),
  ],
  providers: [UserService,ConfigService,ParkingLotService,ReservationService,DatePipe,NgbActiveModal],
  bootstrap: [AppComponent,NgbdModalContent,DoneModalComponent]
})
export class AppModule { }

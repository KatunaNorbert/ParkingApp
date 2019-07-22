import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParkingLotChartsComponent } from './parking-lot-charts.component';

describe('ParkingLotChartsComponent', () => {
  let component: ParkingLotChartsComponent;
  let fixture: ComponentFixture<ParkingLotChartsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParkingLotChartsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParkingLotChartsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

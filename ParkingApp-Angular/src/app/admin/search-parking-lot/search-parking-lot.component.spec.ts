import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchParkingLotComponent } from './search-parking-lot.component';

describe('SearchParkingLotComponent', () => {
  let component: SearchParkingLotComponent;
  let fixture: ComponentFixture<SearchParkingLotComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchParkingLotComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchParkingLotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

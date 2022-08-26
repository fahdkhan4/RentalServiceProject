import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookedCarComponent } from './booked-car.component';

describe('BookedCarComponent', () => {
  let component: BookedCarComponent;
  let fixture: ComponentFixture<BookedCarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BookedCarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BookedCarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateCarOwnerComponent } from './create-car-owner.component';

describe('CreateCarOwnerComponent', () => {
  let component: CreateCarOwnerComponent;
  let fixture: ComponentFixture<CreateCarOwnerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateCarOwnerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateCarOwnerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

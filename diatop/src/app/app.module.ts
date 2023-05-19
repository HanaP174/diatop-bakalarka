import {NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import {MatTabsModule} from "@angular/material/tabs";
import {MatMenuModule} from "@angular/material/menu";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatDialogModule} from "@angular/material/dialog";
import {OrderRequestDialogComponent} from "./order-request-dialog/order-request-dialog.component";
import {MatFormFieldModule} from "@angular/material/form-field";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";
import {HomeComponent} from "./home/home.component";
import {AppRoutingModule} from "./app-routing.module";
import {PdfViewerModule} from "ng2-pdf-viewer";
import {PdfViewerComponentSingle} from "./pdf-viewer/single/pdf-viewer-single.component";
import {NgxExtendedPdfViewerModule} from "ngx-extended-pdf-viewer";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatDividerModule} from "@angular/material/divider";
import {PdfViewerComponentAll} from "./pdf-viewer/all/pdf-viewer-all.component";
import {AccountInfoComponent} from "./account-info/account-info.component";
import {OrdersComponent} from "./account-info/orders/orders.component";
import {MatTableModule} from "@angular/material/table";
import {UserFormComponent} from "./account-info/user-form/user-form.component";
import {MatCardModule} from "@angular/material/card";
import {MatSnackBarModule} from "@angular/material/snack-bar";

@NgModule({
  declarations: [
    AppComponent,
    OrderRequestDialogComponent,
    HomeComponent,
    PdfViewerComponentSingle,
    PdfViewerComponentAll,
    AccountInfoComponent,
    OrdersComponent,
    UserFormComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatTabsModule,
    MatMenuModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatInputModule,
    MatCheckboxModule,
    FormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    AppRoutingModule,
    PdfViewerModule,
    NgxExtendedPdfViewerModule,
    MatSidenavModule,
    MatDividerModule,
    MatTableModule,
    MatCardModule,
    MatSnackBarModule
  ],
  bootstrap: [AppComponent],
  entryComponents: [OrderRequestDialogComponent]
})
export class AppModule { }

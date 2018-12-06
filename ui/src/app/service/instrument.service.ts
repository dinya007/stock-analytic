import {Injectable} from '@angular/core';
import {RestService} from './rest.service';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class InstrumentService {

  constructor(private restService: RestService) {

  }

  public getAllInstruments(): Observable<Array<String>> {
    return this.restService.getArray<String>('/api/instruments');
  }

}

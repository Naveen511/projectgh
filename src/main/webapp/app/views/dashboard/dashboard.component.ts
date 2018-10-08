import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { getStyle, hexToRgba } from '@coreui/coreui/dist/js/coreui-utilities';
import { CustomTooltips } from '@coreui/coreui-plugin-chartjs-custom-tooltips';
import { ZonalService } from 'app/entities/service/zonal.service';
import { SectorService } from 'app/entities/service/sector.service';
import { NurseryService } from 'app/entities/service/nursery.service';
import { OperationalHeadService } from 'app/entities/service/operational-head.service';
import { HttpResponse } from '@angular/common/http';
import { IOperationalHead } from 'app/shared/model/operational-head.model';
import { IZonal } from 'app/shared/model/zonal.model';
import { PickListValueService } from 'app/entities/service/pick-list-value.service';
import { IPickListValue } from 'app/shared/model/pick-list-value.model';
import { NurseryStockService } from 'app/entities/service/nursery-stock.service';
import { INurseryStock } from 'app/shared/model/nursery-stock.model';
import { ISector } from 'app/shared/model/sector.model';
import { INursery } from 'app/shared/model/nursery.model';

@Component({
    templateUrl: 'dashboard.component.html'
})
export class DashboardComponent implements OnInit {
    operationalHeadCount: IOperationalHead[];
    zonalCount: IZonal[];
    sectorCount: ISector[];
    nurseryCount: INursery[];
    pickListValue: IPickListValue[];

    // operationalHeadCount: any;
    // zonalCount: any;
    // sectorCount: any;
    // nurseryCount: any;
    // pickListValue: any;
    public ids: any = [];
    addedQuan: any = [];
    consumedQuan: any = [];
    currentQuan: any = [];

    stockDetailLabel: INurseryStock[];

    // operationalCount: number;
    constructor(
        private operationalHeadService: OperationalHeadService,
        private zonalService: ZonalService,
        private sectorService: SectorService,
        private nurseryService: NurseryService,
        private pickListValueService: PickListValueService,
        private nurseryStockService: NurseryStockService
    ) {}

    // barChart
    public barChartOptions: any = {
        scaleShowVerticalLines: false,
        responsive: true
    };

    public stockDamageLabel: string[] = ['2006', '2007', '2008', '2009', '2010', '2011', '2012'];
    // public stockDetailLabel: string[] = ['2006', '2007', '2008', '2009', '2010', '2011', '2012'];
    public barChartType = 'bar';
    public barChartLegend = true;

    public stockDamageData: any[] = [
        { data: [65, 59, 80, 81, 56, 55, 40], label: 'Godown Stock' },
        { data: [28, 48, 40, 19, 86, 27, 90], label: 'Godown Damage' },
        { data: [65, 59, 80, 81, 56, 55, 40], label: 'Nursery Stock' },
        { data: [28, 48, 40, 19, 86, 27, 90], label: 'Nursery Damage' }
    ];
    public stockDetailData: any[] = [
        { data: this.addedQuan, label: 'Added Quantity' },
        // {data: this.consumedQuan, label: 'Consumed Quantity'},
        { data: this.currentQuan, label: 'Current Quantity' }
    ];

    // Doughnut
    public seedlingCurrentStockLabels: string[] = ['Seedling Stock', 'Current Stock'];
    public seedlingCurrentStockData: number[] = [350, 450];
    public productionLabel: String[] = ['Production', 'Germination', 'Seed Sowing', 'Seed Received'];
    public productionData: number[] = [100, 350, 450, 100];
    public DistributionLabels: string[] = ['Sent', 'Received'];
    public DistributionData: number[] = [350, 450];
    public doughnutChartType = 'doughnut';

    radioModel = 'Month';
    // lineChart1
    public lineChart1Data: Array<any> = [
        {
            data: [65, 59, 84, 84, 51, 55, 40],
            label: 'Series A'
        }
    ];
    public lineChart1Labels: Array<any> = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];
    public lineChart1Options: any = {
        tooltips: {
            enabled: false,
            custom: CustomTooltips
        },
        maintainAspectRatio: false,
        scales: {
            xAxes: [
                {
                    gridLines: {
                        color: 'transparent',
                        zeroLineColor: 'transparent'
                    },
                    ticks: {
                        fontSize: 2,
                        fontColor: 'transparent'
                    }
                }
            ],
            yAxes: [
                {
                    display: false,
                    ticks: {
                        display: false,
                        min: 40 - 5,
                        max: 84 + 5
                    }
                }
            ]
        },
        elements: {
            line: {
                borderWidth: 1
            },
            point: {
                radius: 4,
                hitRadius: 10,
                hoverRadius: 4
            }
        },
        legend: {
            display: false
        }
    };
    public lineChart1Colours: Array<any> = [
        {
            backgroundColor: getStyle('--primary'),
            borderColor: 'rgba(255,255,255,.55)'
        }
    ];
    public lineChart1Legend = false;
    public lineChart1Type = 'line';

    // lineChart2
    public lineChart2Data: Array<any> = [
        {
            data: [1, 18, 9, 17, 34, 22, 11],
            label: 'Series A'
        }
    ];
    public lineChart2Labels: Array<any> = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];
    public lineChart2Options: any = {
        tooltips: {
            enabled: false,
            custom: CustomTooltips
        },
        maintainAspectRatio: false,
        scales: {
            xAxes: [
                {
                    gridLines: {
                        color: 'transparent',
                        zeroLineColor: 'transparent'
                    },
                    ticks: {
                        fontSize: 2,
                        fontColor: 'transparent'
                    }
                }
            ],
            yAxes: [
                {
                    display: false,
                    ticks: {
                        display: false,
                        min: 1 - 5,
                        max: 34 + 5
                    }
                }
            ]
        },
        elements: {
            line: {
                tension: 0.00001,
                borderWidth: 1
            },
            point: {
                radius: 4,
                hitRadius: 10,
                hoverRadius: 4
            }
        },
        legend: {
            display: false
        }
    };
    public lineChart2Colours: Array<any> = [
        {
            // grey
            backgroundColor: getStyle('--info'),
            borderColor: 'rgba(255,255,255,.55)'
        }
    ];
    public lineChart2Legend = false;
    public lineChart2Type = 'line';

    // lineChart3
    public lineChart3Data: Array<any> = [
        {
            data: [78, 81, 80, 45, 34, 12, 40],
            label: 'Series A'
        }
    ];
    public lineChart3Labels: Array<any> = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];
    public lineChart3Options: any = {
        tooltips: {
            enabled: false,
            custom: CustomTooltips
        },
        maintainAspectRatio: false,
        scales: {
            xAxes: [
                {
                    display: false
                }
            ],
            yAxes: [
                {
                    display: false
                }
            ]
        },
        elements: {
            line: {
                borderWidth: 2
            },
            point: {
                radius: 0,
                hitRadius: 10,
                hoverRadius: 4
            }
        },
        legend: {
            display: false
        }
    };
    public lineChart3Colours: Array<any> = [
        {
            backgroundColor: 'rgba(255,255,255,.2)',
            borderColor: 'rgba(255,255,255,.55)'
        }
    ];
    public lineChart3Legend = false;
    public lineChart3Type = 'line';

    // barChart1
    public barChart1Data: Array<any> = [
        {
            data: [78, 81, 80, 45, 34, 12, 40, 78, 81, 80, 45, 34, 12, 40, 12, 40],
            label: 'Series A'
        }
    ];
    public barChart1Labels: Array<any> = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16'];
    public barChart1Options: any = {
        tooltips: {
            enabled: false,
            custom: CustomTooltips
        },
        maintainAspectRatio: false,
        scales: {
            xAxes: [
                {
                    display: false,
                    barPercentage: 0.6
                }
            ],
            yAxes: [
                {
                    display: false
                }
            ]
        },
        legend: {
            display: false
        }
    };
    public barChart1Colours: Array<any> = [
        {
            backgroundColor: 'rgba(255,255,255,.3)',
            borderWidth: 0
        }
    ];
    public barChart1Legend = false;
    public barChart1Type = 'bar';

    // mainChart

    public mainChartElements = 27;
    public mainChartData1: Array<number> = [];
    public mainChartData2: Array<number> = [];
    public mainChartData3: Array<number> = [];

    public mainChartData: Array<any> = [
        {
            data: this.mainChartData1,
            label: 'Current'
        },
        {
            data: this.mainChartData2,
            label: 'Previous'
        },
        {
            data: this.mainChartData3,
            label: 'BEP'
        }
    ];
    /* tslint:disable:max-line-length */
    public mainChartLabels: Array<any> = [
        'Monday',
        'Tuesday',
        'Wednesday',
        'Thursday',
        'Friday',
        'Saturday',
        'Sunday',
        'Monday',
        'Tuesday',
        'Wednesday',
        'Thursday',
        'Friday',
        'Saturday',
        'Sunday',
        'Monday',
        'Tuesday',
        'Wednesday',
        'Thursday',
        'Friday',
        'Saturday',
        'Sunday',
        'Monday',
        'Thursday',
        'Wednesday',
        'Thursday',
        'Friday',
        'Saturday',
        'Sunday'
    ];
    /* tslint:enable:max-line-length */
    public mainChartOptions: any = {
        tooltips: {
            enabled: false,
            custom: CustomTooltips,
            intersect: true,
            mode: 'index',
            position: 'nearest',
            callbacks: {
                labelColor: function(tooltipItem, chart) {
                    return { backgroundColor: chart.data.datasets[tooltipItem.datasetIndex].borderColor };
                }
            }
        },
        responsive: true,
        maintainAspectRatio: false,
        scales: {
            xAxes: [
                {
                    gridLines: {
                        drawOnChartArea: false
                    },
                    ticks: {
                        callback: function(value: any) {
                            return value.charAt(0);
                        }
                    }
                }
            ],
            yAxes: [
                {
                    ticks: {
                        beginAtZero: true,
                        maxTicksLimit: 5,
                        stepSize: Math.ceil(250 / 5),
                        max: 250
                    }
                }
            ]
        },
        elements: {
            line: {
                borderWidth: 2
            },
            point: {
                radius: 0,
                hitRadius: 10,
                hoverRadius: 4,
                hoverBorderWidth: 3
            }
        },
        legend: {
            display: false
        }
    };
    public mainChartColours: Array<any> = [
        {
            // brandInfo
            backgroundColor: hexToRgba(getStyle('--info'), 10),
            borderColor: getStyle('--info'),
            pointHoverBackgroundColor: '#fff'
        },
        {
            // brandSuccess
            backgroundColor: 'transparent',
            borderColor: getStyle('--success'),
            pointHoverBackgroundColor: '#fff'
        },
        {
            // brandDanger
            backgroundColor: 'transparent',
            borderColor: getStyle('--danger'),
            pointHoverBackgroundColor: '#fff',
            borderWidth: 1,
            borderDash: [8, 5]
        }
    ];
    public mainChartLegend = false;
    public mainChartType = 'line';

    // social box charts

    public brandBoxChartData1: Array<any> = [
        {
            data: [65, 59, 84, 84, 51, 55, 40],
            label: 'Facebook'
        }
    ];
    public brandBoxChartData2: Array<any> = [
        {
            data: [1, 13, 9, 17, 34, 41, 38],
            label: 'Twitter'
        }
    ];
    public brandBoxChartData3: Array<any> = [
        {
            data: [78, 81, 80, 45, 34, 12, 40],
            label: 'LinkedIn'
        }
    ];
    public brandBoxChartData4: Array<any> = [
        {
            data: [35, 23, 56, 22, 97, 23, 64],
            label: 'Google+'
        }
    ];

    public brandBoxChartLabels: Array<any> = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];
    public brandBoxChartOptions: any = {
        tooltips: {
            enabled: false,
            custom: CustomTooltips
        },
        responsive: true,
        maintainAspectRatio: false,
        scales: {
            xAxes: [
                {
                    display: false
                }
            ],
            yAxes: [
                {
                    display: false
                }
            ]
        },
        elements: {
            line: {
                borderWidth: 2
            },
            point: {
                radius: 0,
                hitRadius: 10,
                hoverRadius: 4,
                hoverBorderWidth: 3
            }
        },
        legend: {
            display: false
        }
    };
    public brandBoxChartColours: Array<any> = [
        {
            backgroundColor: 'rgba(255,255,255,.1)',
            borderColor: 'rgba(255,255,255,.55)',
            pointHoverBackgroundColor: '#fff'
        }
    ];
    public brandBoxChartLegend = false;
    public brandBoxChartType = 'line';

    public random(min: number, max: number) {
        return Math.floor(Math.random() * (max - min + 1) + min);
    }

    ngOnInit(): void {
        // generate random values for mainChart
        // for (let i = 0; i <= this.mainChartElements; i++) {
        //  this.mainChartData1.push(this.random(50, 200));
        //             this.mainChartData2.push(this.random(80, 100));
        //           this.mainChartData3.push(65);
        //     }

        // Get the count of operational head
        this.operationalHeadService.getOperationalHeadCount().subscribe((res: HttpResponse<IOperationalHead[]>) => {
            this.operationalHeadCount = res.body;
            // this.operationalCount = this.operationalHeadCount.length;
            // console.log('count', this.operationalHeadCount);
        });

        // Get the count of Zonal
        this.zonalService.getZonalCount().subscribe((res: HttpResponse<IZonal[]>) => {
            this.zonalCount = res.body;
            // console.log('count', this.zonalCount);
        });

        // Get the count of Sector
        this.sectorService.getSectorCount().subscribe((res: HttpResponse<IZonal[]>) => {
            this.sectorCount = res.body;
            // console.log('count', this.sectorCount);
        });

        // Get the count of Zonal
        this.nurseryService.getNurseryCount().subscribe((res: HttpResponse<IZonal[]>) => {
            this.nurseryCount = res.body;
            // console.log('count', this.nurseryCount);
        });

        this.nurseryStockService.particularNursery(1401).subscribe((res: HttpResponse<INurseryStock[]>) => {
            // console.log("category", res.body);
            this.stockDetailLabel = res.body;
            console.log('category', this.stockDetailLabel);
            for (const result of this.stockDetailLabel) {
                this.ids.push(result.pickListCategoryPickListValue);
                this.addedQuan.push(result.addedQuantity);
                this.consumedQuan.push(result.consumedQuantity);
                this.currentQuan.push(result.currentQuantity);
                // console.log("ids", this.ids);
            }
            console.log('Category', this.ids);
            // console.log("addedQuantity", this.addedQuan);
            // console.log("consumedQuantity", this.consumedQuan);
            // console.log("currentQuantity", this.currentQuan);
        });

        // this.pickListValueService.query().subscribe((res: HttpResponse<IPickListValue[]>) => {
        //     // console.log("category", res.body);
        //     this.stockDetailLabel = res.body;
        //     console.log("category", this.stockDetailLabel);
        // });
    }
}

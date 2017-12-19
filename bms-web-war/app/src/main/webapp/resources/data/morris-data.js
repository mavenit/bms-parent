$(function() {

    Morris.Area({
        element: 'morris-area-chart',
        data: [{
            period: '2016 Q1',
            Completed: 50,
            Pending: null,
            NotResolved: 1
        }, {
            period: '2016 Q2',
            Completed: 30,
            Pending: null,
            NotResolved: 2
        }, {
            period: '2016 Q3',
            Completed: 25,
            Pending: null,
            NotResolved: null
        }, {
            period: '2016 Q4',
            Completed: 10,
            Pending: null,
            NotResolved: 3
        }, {
            period: '2017 Q1',
            Completed: 30,
            Pending: 2,
            NotResolved: 4
        }, {
            period: '2017 Q2',
            Completed: 16,
            Pending: 2,
            NotResolved: null
        }, {
            period: '2017 Q3',
            Completed: 20,
            Pending: null,
            NotResolved: 1
        }, {
            period: '2017 Q4',
            Completed: 15,
            Pending: 5,
            NotResolved: 2
        }],
        xkey: 'period',
        ykeys: ['Completed', 'Pending', 'NotResolved'],
        labels: ['Completed', 'Pending', 'NotResolved'],
        pointSize: 2,
        hideHover: 'auto',
        resize: true
    });

    Morris.Donut({
        element: 'morris-donut-chart',
        data: [{
            label: "Completed Tasks",
            value: 70
        }, {
            label: "Pending Tasks",
            value: 30
        }],
        resize: true
    });

    Morris.Bar({
        element: 'morris-bar-chart',
        data: [{
            y: '2017',
            a: 182526,
            b: 12352
        }, {
            y: '2016',
            a: 176656,
            b: 2505
        }, {
            y: '2015',
            a: 146352,
            b: 13585
        }, {
           y: '2014',
            a: 132526,
            b: 12352
        }, {
           y: '2013',
            a: 126352,
            b: 13585
        }, {
             y: '2012',
            a: 106656,
            b: 2505
        }, {
           y: '2011',
            a: 55652,
            b: 23523
        }],
        xkey: 'y',
        ykeys: ['a', 'b'],
        labels: ['Collected Payment', 'Pending Payment'],
        hideHover: 'auto',
        resize: true
    });
    
});
